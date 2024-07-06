package com.balduvian.minecraftbingo

import com.balduvian.minecraftbingo.bingo.PlayerData
import org.bukkit.Color
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Display
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import org.joml.Vector3f

object FloatingItem {
	fun createDisplay(player: Player): ItemDisplay {
		val display = player.world.spawn(player.location, ItemDisplay::class.java)

		display.velocity = Vector(0.0, 0.0, 0.0)
		display.setGravity(false)
		display.isInvulnerable = true
		display.isVisualFire = false
		display.isGlowing = true

		display.brightness = Display.Brightness(15, 15)
		display.billboard = Display.Billboard.CENTER
		display.shadowStrength = 0.0f
		display.viewRange = 1.0f
		display.teleportDuration = 1
		display.itemDisplayTransform = ItemDisplay.ItemDisplayTransform.GROUND

		player.addPassenger(display)

		return display
	}

	fun isSamePosition(location0: Location, location1: Location?): Boolean {
		if (location1 == null) return false
		return location0.x == location1.x && location0.y == location1.y && location0.z == location1.z
	}

	fun floatingItemsTick(player: Player?, playerData: PlayerData) {
		val board = playerData.board
		val items = playerData.floatingItems

		if (player != null && isSamePosition(player.location, playerData.lastLocation)) {
			++playerData.stillTicks
		} else {
			playerData.stillTicks = 0
		}

		if (board == null || player == null || player.gameMode === GameMode.SPECTATOR) {
			for (i in items.indices) {
				val item = items[i]
				if (item != null && item.isValid) {
					item.remove()
					items[i] = null
				}
			}
		} else {
			for (i in board.grid.indices) {
				val gridY = 4 - (i / 5)
				val gridX = i % 5

				val display = items[i]?.let { if (it.isValid) it else null } ?: createDisplay(player)
				items[i] = display

				if (display.vehicle !== player || display.world !== player.world) {
					display.leaveVehicle()
					player.addPassenger(display)
				}

				val transform = display.transformation
				transform.translation.set(
					Vector3f(gridX * 0.0625f - 0.4f, gridY * 0.0625f - 0.3125f, -0.25f)
				)
				transform.scale.set(0.125f, 0.125f, 0.125f);
				display.transformation = transform

				val itemStack = display.itemStack?.let { if (it.type == board.grid[i]) it else null } ?: ItemStack(board.grid[i])
				if (board.obtained[i]) {
					itemStack.editMeta { it.setEnchantmentGlintOverride(true) }
					display.glowColorOverride = Color.ORANGE
				} else {
					itemStack.editMeta { it.setEnchantmentGlintOverride(false) }
					display.glowColorOverride = Color.BLUE
				}
				display.itemStack = itemStack
			}

			playerData.lastLocation = player.location
		}
	}
}