package com.balduvian.minecraftbingo

data class BoardSettings(var visibility : BoardVisibility = BoardVisibility.ALWAYS, var hotbarSlot : Int = 8)

enum class BoardVisibility {
	HIDDEN, ALWAYS, SLOT
}
