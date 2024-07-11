package com.balduvian.minecraftbingo

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.generator.BiomeProvider
import java.io.File

object WorldManager {
	const val OVERWORLD_NAME = "world"
	const val GAME_WORLD_NAME = "world_game"

	private var overworld: World? = null
	var gameWorld: World? = null
		private set

	fun load() {
		overworld = Bukkit.getWorld(OVERWORLD_NAME)
	}

	fun getOverworld() = overworld ?: throw Exception("no overworld")

	fun createGameWorld(): World {
		gameWorld?.let { world ->
			Bukkit.unloadWorld(world, false)
		}
		File(GAME_WORLD_NAME).deleteRecursively()

		val newWorld = Bukkit.createWorld(WorldCreator(GAME_WORLD_NAME).environment(World.Environment.NORMAL))
			?: throw Exception("could not create world")
		gameWorld = newWorld

		return newWorld
	}
}