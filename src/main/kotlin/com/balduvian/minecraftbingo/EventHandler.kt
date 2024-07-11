package com.balduvian.minecraftbingo

import com.balduvian.minecraftbingo.bingo.Game
import com.balduvian.minecraftbingo.bingo.PlayerData
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.*
import org.bukkit.event.player.PlayerBucketEmptyEvent
import org.bukkit.event.player.PlayerBucketEntityEvent
import org.bukkit.event.player.PlayerBucketEvent
import org.bukkit.event.player.PlayerBucketFillEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.util.Vector
import kotlin.math.PI
import kotlin.random.Random

class EventHandler : Listener {
	fun onCollectItem(player: Player, material: Material) {
		val game = Game.ongoingGame ?: return
		val playerData = PlayerData.get(player.uniqueId)
		val board = playerData.board ?: return
		val materialIndex = board.grid.indexOf(material).let { if (it == -1) return else it }
		if (board.obtained[materialIndex]) return

		game.obtainItem(player, board, materialIndex)
	}

	@EventHandler
	fun onPickUpItem(event: EntityPickupItemEvent) {
		onCollectItem(event.entity as? Player ?: return, event.item.itemStack.type)
	}

	@EventHandler
	fun onBucketEmptyEvent(event: PlayerBucketEmptyEvent) {
		val bucket = event.itemStack ?: return
		onCollectItem(event.player, bucket.type)
	}

	@EventHandler
	fun onBucketEntityEvent(event: PlayerBucketEntityEvent) {
		onCollectItem(event.player, event.entityBucket.type)
	}

	@EventHandler
	fun onBucketFillEvent(event: PlayerBucketFillEvent) {
		val bucket = event.itemStack ?: return
		onCollectItem(event.player, bucket.type)
	}

	@EventHandler
	fun onCraftItem(event: CraftItemEvent) {
		onCollectItem(event.whoClicked as? Player ?: return, event.recipe.result.type)
	}

	@EventHandler
	fun interactEvent(event: InventoryClickEvent) {
		if (event.inventory.holder is BingoHolder) {
			event.isCancelled = true
		}
	}

	@EventHandler
	fun onCraftItem(event: InventoryClickEvent) {
		if (event.isCancelled) return

		if (event.action === InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			onCollectItem(event.whoClicked as? Player ?: return, event.currentItem?.type ?: return)
		} else if (event.action === InventoryAction.PLACE_ONE || event.action === InventoryAction.PLACE_SOME || event.action === InventoryAction.PLACE_ALL) {
			onCollectItem(
				event.whoClicked as? Player ?: return,
				event.cursor.type
			)
		}
	}

	@EventHandler
	fun onRespawn(event: PlayerRespawnEvent) {
		val game = Game.ongoingGame ?: return
		if (!game.playersUIDs.contains(event.player.uniqueId)) return

		val world = WorldManager.gameWorld ?: return

		val spawnBlock = getSpawnBlock(world, event.player, game)

		val newLocation = spawnBlock.location.toCenterLocation()
		newLocation.y = getTopYLevel(spawnBlock).toDouble()

		event.respawnLocation = newLocation
	}

	private fun getSpawnBlock(world: World, player: Player, game: Game): Block {
		val playerLocations = Bukkit.getOnlinePlayers().filter {
			it.uniqueId != player.uniqueId &&
				game.playersUIDs.contains(it.uniqueId) &&
				it.location.world === world
		}.map { it.location }
			.ifEmpty { listOf(Location(world, 0.0, 0.0, 0.0)) }

		val referenceLocation = playerLocations.random()
		val directionVector = Vector()

		for (otherLocation in playerLocations) {
			if (otherLocation === referenceLocation) continue
			directionVector.add(referenceLocation.toVector().setY(0.0).subtract(otherLocation.toVector().setY(0.0)))
		}
		if (directionVector.isZero) directionVector.setX(1.0).rotateAroundY(Random.nextDouble(0.0, PI * 2.0))

		directionVector.normalize().multiply(Game.PLAYER_RADIUS)

		return referenceLocation.add(directionVector).block
	}

	private fun getTopYLevel(block: Block): Int {
		val world = block.world
		for (y in 319 downTo 0) {
			if (!world.getBlockAt(block.x, y, block.z).isPassable) {
				return y + 1
			}
		}
		return 0
	}
}
