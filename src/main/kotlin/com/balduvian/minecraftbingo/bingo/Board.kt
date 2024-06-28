package com.balduvian.minecraftbingo.bingo

import com.balduvian.minecraftbingo.BingoHolder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class Board(val grid: Array<Material>, val obtained: BooleanArray) {
	companion object {
		fun generateRandomGrid(): Board {
			val array = Array(25) {
				var material = Material.entries.random()
				while (!material.isItem) {
					material = Material.entries.random()
				}
				material
			}

			return Board(array, BooleanArray(25))
		}

		fun generateResources(): Board {
			val groups = ResourceGroup.entries.shuffled().take(25)
			return Board(Array(25) { index ->
				groups[index].materials.random()
			}, BooleanArray(25))
		}
	}

	fun clone(): Board {
		return Board(grid.clone(), obtained.clone())
	}

	fun createInventory(player: Player): Inventory {
		val inventory = Bukkit.createInventory(
			BingoHolder(),
			5 * 9,
			Component.text("${player.name}'s", NamedTextColor.GOLD)
				.append(Component.text(" Bingo Board", NamedTextColor.GRAY))
		)

		for (i in 0..24) {
			val y = i / 5
			val x = i % 5 + 2

			val material = grid[i]
			val isObtained = obtained[i]

			val item = ItemStack(material)

			if (isObtained) item.editMeta {
				it.setEnchantmentGlintOverride(true)
				it.displayName(Component.text(material.name, NamedTextColor.GOLD, TextDecoration.BOLD))
			}

			inventory.setItem(y * 9 + x, item)
		}

		return inventory
	}
}