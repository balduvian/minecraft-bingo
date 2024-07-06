package com.balduvian.minecraftbingo.command

import com.balduvian.minecraftbingo.WorldManager
import com.balduvian.minecraftbingo.bingo.Board
import com.balduvian.minecraftbingo.bingo.Game
import com.balduvian.minecraftbingo.bingo.PlayerData
import io.papermc.paper.command.brigadier.BasicCommand
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Snowball
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import java.util.*

class GenerateBoardsCommand : BasicCommand {
	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val sender = CommandUtil.getSenderPlayer(commandSourceStack, true) ?: return

		val board = Board.generateResources()

		Bukkit.getOnlinePlayers().forEach { player ->
			val playerData = PlayerData.get(player.uniqueId)
			playerData.board = board.clone()

			val inventory = board.createInventory(sender)
			sender.openInventory(inventory)
		}

		sender.sendMessage("generated a new random board")
	}
}

class OpenBoardCommand : BasicCommand {
	override fun suggest(commandSourceStack: CommandSourceStack, args: Array<String>) =
		if (args.isEmpty() || args.size == 1) CommandUtil.suggestPlayers(args) else emptyList()

	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val sender = CommandUtil.getSenderPlayer(commandSourceStack) ?: return

		val boardPlayer = args.firstOrNull()?.let { name ->
			Bukkit.getOfflinePlayerIfCached(name) ?: return sender.sendMessage("no player found")
		} ?: sender

		val board = PlayerData.get(boardPlayer.uniqueId).board ?: run {
			sender.sendMessage("${if (boardPlayer.uniqueId == sender.uniqueId) "you don't" else "${sender.name} doesn't" } have a board")
			return
		}

		val inventory = board.createInventory(boardPlayer)
		sender.openInventory(inventory)
	}
}

class StartGameCommand : BasicCommand {
	override fun suggest(commandSourceStack: CommandSourceStack, args: Array<String>) = CommandUtil.suggestPlayers(args)

	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val sender = CommandUtil.getOpSender(commandSourceStack) ?: return

		if (Game.ongoingGame != null) Game.endGame()

		val onlinePlayers = Bukkit.getOnlinePlayers()

		val gamePlayers = args.mapNotNull { name ->
			try {
				onlinePlayers.find { player -> player.name == name }
			} catch (ex: Throwable) {
				null
			}
		} as ArrayList<Player>

		if (gamePlayers.isEmpty()) return sender.sendMessage("no players in game")

		if (Game.startGame(gamePlayers)) {
			sender.sendMessage("game started")
		} else {
			Game.endGame()
			sender.sendMessage("game could not start")
		}
	}
}

class EndGameCommend : BasicCommand {
	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val sender = CommandUtil.getOpSender(commandSourceStack) ?: return

		if (Game.ongoingGame == null) return sender.sendMessage("game not going")

		Game.endGame()

		sender.sendMessage("game stopped")

		val gameWorld = WorldManager.gameWorld ?: return
		val overworld = WorldManager.getOverworld()

		Bukkit.getOnlinePlayers().filter { player -> player.world === gameWorld }.forEach { player ->
			player.gameMode = GameMode.SURVIVAL
			player.teleport(overworld.spawnLocation)
		}
	}
}

class SpectateCommand : BasicCommand {
	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val player = CommandUtil.getSenderPlayer(commandSourceStack) ?: return

		val game = Game.ongoingGame ?: return player.sendMessage("game is not going")

		if (!game.isPlayerFinished(player.uniqueId)) return player.sendMessage("you are not finished")

		player.gameMode = GameMode.SPECTATOR
	}
}

/* testing */

class NewRandomBoardCommand : BasicCommand {
	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val sender = CommandUtil.getSenderPlayer(commandSourceStack, true) ?: return

		val playerData = PlayerData.get(sender.uniqueId)

		val board = Board.generateRandomGrid()
		playerData.board = board

		val inventory = board.createInventory(sender)
		sender.openInventory(inventory)

		sender.sendMessage("generated a new random board")
	}
}

class ClearBoardCommand : BasicCommand {
	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val sender = CommandUtil.getSenderPlayer(commandSourceStack, true) ?: return

		val playerData = PlayerData.get(sender.uniqueId)

		val board = playerData.board ?: Board.generateResources()
		for (i in 0..24) board.obtained[i] = false
		playerData.board = board

		sender.sendMessage("reset board")
	}
}

class TestVectorCommand : BasicCommand {
	override fun execute(commandSourceStack: CommandSourceStack, args: Array<String>) {
		val player = CommandUtil.getSenderPlayer(commandSourceStack) ?: return

		val lookDirection = player.eyeLocation.direction

		val globUp = Vector(0.0, 1.0, 0.0)

		val left = globUp.crossProduct(lookDirection).normalize()
		val up = lookDirection.clone().multiply(-1).crossProduct(globUp).normalize().multiply(-1)

		for (y in 0 until 5) {
			for (x in 0 until 5) {
				val location = player.eyeLocation
					.add(lookDirection.clone().multiply(7.0))
					.add(up.clone().multiply(y + 2))
					.add(left.clone().multiply(x + 7.5))

				val entity = player.world.spawn(location, Snowball::class.java)
				entity.velocity = Vector(0.0, 0.0, 0.0)
				entity.setGravity(false)
				entity.isInvulnerable = true
				entity.isVisualFire = false
				val stack = ItemStack(Material.GOLDEN_CARROT)
				if (y == 2) {
					stack.editMeta { it.setEnchantmentGlintOverride(true) }
					entity.isGlowing = true
				}
				entity.item = stack
			}
		}
	}
}
