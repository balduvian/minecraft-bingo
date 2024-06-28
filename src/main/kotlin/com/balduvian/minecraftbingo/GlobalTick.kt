package com.balduvian.minecraftbingo

import com.balduvian.minecraftbingo.bingo.PlayerData
import org.bukkit.Bukkit

object GlobalTick {
	fun start() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BingoPlugin.instance, ::onTick, 0, 1)
	}

	fun onTick() {
		PlayerData.playerDataMap.forEach { (uuid, playerData) ->
			FloatingItem.floatingItemsTick(Bukkit.getPlayer(uuid), playerData)
		}
	}
}