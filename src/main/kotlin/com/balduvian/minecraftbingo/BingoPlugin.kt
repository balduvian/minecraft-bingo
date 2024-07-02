package com.balduvian.minecraftbingo


import com.balduvian.minecraftbingo.command.*
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BingoPlugin : JavaPlugin() {
	override fun onEnable() {
		instance = this

		Bukkit.getPluginManager().registerEvents(EventHandler(), this)
		lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) { event ->
			val commands = event.registrar()

			commands.register("gen-boards", "generate new boards", GenerateBoardsCommand())
			commands.register("board", "see your board", OpenBoardCommand())
			commands.register("start-game", "start a game", StartGameCommand())
			commands.register("end-game", "end the current game", EndGameCommend())
			commands.register("spec", "spectate once finished", SpectateCommand())

			commands.register("test-board", "test generate a new board", NewRandomBoardCommand())
			commands.register("clear-board", "test clear your board", ClearBoardCommand())
			commands.register("tvc", "test item vector", TestVectorCommand())
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, { WorldManager.load() }, 0)
		GlobalTick.start()
	}

	companion object {
		lateinit var instance: BingoPlugin
	}
}
