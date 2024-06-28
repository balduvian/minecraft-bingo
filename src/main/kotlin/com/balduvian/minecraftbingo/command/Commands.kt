package com.balduvian.minecraftbingo.command

import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.ArrayList

object Commands {
	fun getSenderPlayer(commandSourceStack: CommandSourceStack, needsOp: Boolean = false): Player? {
		val player = commandSourceStack.sender as? Player
		if (player == null) {
			commandSourceStack.sender.sendMessage("you must be a player to execute this command")
			return null
		}
		if (!player.isOp && needsOp) {
			commandSourceStack.sender.sendMessage("you must be an op to execute this command")
			return null
		}
		return player
	}

	fun getOpSender(commandSourceStack: CommandSourceStack): CommandSender? {
		val sender = commandSourceStack.sender
		if (sender is Player && !sender.isOp) {
			commandSourceStack.sender.sendMessage("you must be an op to execute this command")
			return null
		}
		return sender
	}

	fun suggestPlayers(args: Array<String>): ArrayList<String> {
		return Bukkit.getOnlinePlayers().map { it.name }.filter { !args.contains(it) } as ArrayList<String>
	}
}