package com.balduvian.minecraftbingo.bingo

import com.balduvian.minecraftbingo.BingoPlugin
import com.balduvian.minecraftbingo.WorldManager
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

fun main () {
	for (numPlayers in 1..16) {
		println("num players        : " + numPlayers)
		println("stride angle       : " + Game.getStrideAngle(numPlayers))
		println("spread radius      : " + Game.getSpreadRadius(numPlayers))
		println("starting locations : " + Game.chooseStartingLocations(numPlayers).joinToString(", ", "[", "]") { (x, y) -> "(${"%.2f".format(x)}, ${"%.2f".format(y)})" })
		println()
	}
}

class Game(var playersUIDs: ArrayList<UUID>, var time: Int, var placements: ArrayList<UUID>) {
	companion object {
		var ongoingGame: Game? = null
		private var gameTickTask: Int? = null

		fun startGame(players: ArrayList<Player>): Boolean {
			val world = WorldManager.createGameWorld()

			ongoingGame = Game(players.map { it.uniqueId } as ArrayList<UUID>, 0, ArrayList())

			distributePlayers(world, players)

			gameTickTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(BingoPlugin.instance, ::gameTick, 0L, 1L)
			return gameTickTask != -1
		}

		fun getStrideAngle(numPlayers: Int) = PI * 2.0 / numPlayers.toDouble()

		fun getSpreadRadius(numPlayers: Int) = PLAYER_RADIUS / (2.0 * sin(getStrideAngle(numPlayers.coerceAtLeast(2)) / 2.0))

		fun chooseStartingLocations(numPlayers: Int): List<Pair<Double, Double>> {
			val startingAngle = Random.nextDouble(0.0, PI * 2.0)
			val strideAngle = getStrideAngle(numPlayers)

			return (0..<numPlayers).map { i ->
				Math.cos(startingAngle + strideAngle * i) * getSpreadRadius(numPlayers) to
				Math.sin(startingAngle + strideAngle * i) * getSpreadRadius(numPlayers)
			}
		}

		const val STARTING_Y_LEVEL = 256.0
		const val PLAYER_RADIUS = 4000.0
		val ELYTRA_KEY = NamespacedKey(BingoPlugin.instance, "elytra")

		fun distributePlayers(world: World, players: ArrayList<Player>) {
			val startingLocations = chooseStartingLocations(players.size)

			world.time = 0L
			world.weatherDuration = 0
			world.setStorm(false)
			world.difficulty = Difficulty.NORMAL

			players.zip(startingLocations).forEach { (player, startingLocation) ->
				player.inventory.clear()
				player.clearActiveItem()
				player.itemOnCursor.let { if (!it.isEmpty) it.amount = 0 }
				player.clearActivePotionEffects()
				player.exp = 0.0f
				player.level = 0
				player.fireTicks = 0
				player.fallDistance = 0.0f
				player.health = 20.0
				player.foodLevel = 20
				player.saturation = 5.0f
				player.gameMode = GameMode.SURVIVAL

				val elytraItem = ItemStack(Material.ELYTRA)
				elytraItem.editMeta {
					it.persistentDataContainer.set(ELYTRA_KEY, PersistentDataType.BYTE, 1)
					it.addEnchant(Enchantment.BINDING_CURSE, 1, true)
					it.addEnchant(Enchantment.VANISHING_CURSE, 1, true)
					it.addItemFlags(ItemFlag.HIDE_ENCHANTS)
					it.displayName(Component.text("Bingo Parachute", NamedTextColor.GOLD))
				}

				val passengers = player.passengers
				passengers.forEach { player.removePassenger(it) }

				if (!player.teleport(Location(world, startingLocation.first, STARTING_Y_LEVEL, startingLocation.second)))
					throw Error("could not teleport!")
				player.inventory.setItem(EquipmentSlot.CHEST, elytraItem)
				player.addPotionEffect(PotionEffect(PotionEffectType.RESISTANCE, 1200, 100))

				Bukkit.getScheduler().scheduleSyncDelayedTask(BingoPlugin.instance, { player.isGliding = true }, 20)

				passengers.forEach { player.addPassenger(it) }
			}
		}

		fun gameTick() {
			val game = ongoingGame ?: return

			if (!game.isFinished()) ++game.time

			if (game.time > 0 && game.time % 600 == 0) {
				game.clearElytras()
			}
		}

		fun endGame() {
			ongoingGame = null
			gameTickTask?.let { Bukkit.getScheduler().cancelTask(it) }
			gameTickTask = null
		}
	}

	private fun clearElytras() {
		playersUIDs.mapNotNull { Bukkit.getPlayer(it) }.forEach { player ->
			player.inventory.forEach { item ->
				if (item != null && item.hasItemMeta() && item.itemMeta.persistentDataContainer.has(ELYTRA_KEY)) {
					item.amount = 0
				}
			}
		}
	}

	private fun playerFinish(uuid: UUID) {
		placements.add(uuid)

		val player = Bukkit.getOfflinePlayer(uuid)

		Bukkit.getOnlinePlayers().forEach { it.sendMessage(
			Component.text(player.name ?: "[Unknown]", NamedTextColor.GOLD, TextDecoration.BOLD)
				.append(Component.text(" finished! place: "))
				.append(Component.text(placements.size, NamedTextColor.GOLD, TextDecoration.BOLD))
		) }
	}

	fun obtainItem(player: Player, board: Board, materialIndex: Int) {
		if (isFinished()) return

		val material = board.grid[materialIndex]
		board.obtained[materialIndex] = true

		val numGotten = board.obtained.count { it }

		player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f)

		Bukkit.getOnlinePlayers().forEach {
			it.sendMessage(
				Component.text(player.name, NamedTextColor.GOLD)
					.append(Component.text(" obtained "))
					.append(
						Component.text(material.name, NamedTextColor.GOLD, TextDecoration.BOLD)
					)
					.append(Component.text(" (${numGotten} / 25)"))
			)
		}

		if (numGotten == 25) {
			playerFinish(player.uniqueId)

			if (placements.size == playersUIDs.size - 1) {
				val lastUUID = playersUIDs.find { uuid -> !placements.contains(uuid) } ?: throw Exception("no last player")
				playerFinish(lastUUID)
			}
		}

		if (isFinished()) {
			Bukkit.getOnlinePlayers().forEach {
				it.sendMessage(Component.text("game complete"))
			}
		}
	}

	fun isFinished() = placements.size == playersUIDs.size

	fun isPlayerFinished(uuid: UUID) = placements.contains(uuid)
}