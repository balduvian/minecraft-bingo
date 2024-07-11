package com.balduvian.minecraftbingo.bingo

import com.balduvian.minecraftbingo.BoardSettings
import org.bukkit.Location
import org.bukkit.entity.ItemDisplay
import java.util.UUID

class PlayerData(var board: Board?, var floatingItems: Array<ItemDisplay?>, var stillTicks: Int, var lastLocation: Location?, val settings : BoardSettings = BoardSettings()) {
	companion object {
		val playerDataMap = HashMap<UUID, PlayerData>()

		operator fun get(playerId: UUID): PlayerData {
			return playerDataMap.getOrPut(playerId) { PlayerData(null, arrayOfNulls(25), 0, null) }
		}
	}
}