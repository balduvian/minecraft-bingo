package com.balduvian.minecraftbingo.bingo

import org.bukkit.Location
import org.bukkit.entity.ItemDisplay
import java.util.UUID

class PlayerData(var board: Board?, var floatingItems: Array<ItemDisplay?>, var stillTicks: Int, var lastLocation: Location?) {
	companion object {
		val playerDataMap = HashMap<UUID, PlayerData>()

		fun get(playerId: UUID): PlayerData {
			return playerDataMap.getOrPut(playerId) { PlayerData(null, arrayOfNulls(25), 0, null) }
		}
	}
}