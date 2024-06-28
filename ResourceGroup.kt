package com.balduvian.minecraftbingo.bingo

import org.bukkit.Material

enum class ResourceGroup (val materials: List<Material>) {
	COLORED_WOOL(listOf(
		Material.WHITE_WOOL,
		Material.LIGHT_GRAY_WOOL,
		Material.GRAY_WOOL,
		Material.BLACK_WOOL,
		Material.RED_WOOL,
		Material.ORANGE_WOOL,
		Material.YELLOW_WOOL,
		Material.LIGHT_BLUE_WOOL,
		Material.BLUE_WOOL,
		Material.PURPLE_WOOL,
		Material.MAGENTA_WOOL,
		Material.PINK_WOOL,
		Material.WHITE_CARPET,
		Material.LIGHT_GRAY_CARPET,
		Material.GRAY_CARPET,
		Material.BLACK_CARPET,
		Material.RED_CARPET,
		Material.ORANGE_CARPET,
		Material.YELLOW_CARPET,
		Material.LIGHT_BLUE_CARPET,
		Material.BLUE_CARPET,
		Material.PURPLE_CARPET,
		Material.MAGENTA_CARPET,
		Material.PINK_CARPET,
	)),
	COLORED_WOOL_2(listOf(
		Material.WHITE_BANNER,
		Material.LIGHT_GRAY_BANNER,
		Material.GRAY_BANNER,
		Material.BLACK_BANNER,
		Material.RED_BANNER,
		Material.ORANGE_BANNER,
		Material.YELLOW_BANNER,
		Material.LIGHT_BLUE_BANNER,
		Material.BLUE_BANNER,
		Material.PURPLE_BANNER,
		Material.MAGENTA_BANNER,
		Material.PINK_BANNER,
		Material.WHITE_BED,
		Material.LIGHT_GRAY_BED,
		Material.GRAY_BED,
		Material.BLACK_BED,
		Material.RED_BED,
		Material.ORANGE_BED,
		Material.YELLOW_BED,
		Material.LIGHT_BLUE_BED,
		Material.BLUE_BED,
		Material.PURPLE_BED,
		Material.MAGENTA_BED,
		Material.PINK_BED,
	)),
	COLORED_TERRACOTTA(listOf(
		Material.WHITE_TERRACOTTA,
		Material.LIGHT_GRAY_TERRACOTTA,
		Material.GRAY_TERRACOTTA,
		Material.BLACK_TERRACOTTA,
		Material.RED_TERRACOTTA,
		Material.ORANGE_TERRACOTTA,
		Material.YELLOW_TERRACOTTA,
		Material.LIGHT_BLUE_TERRACOTTA,
		Material.BLUE_TERRACOTTA,
		Material.PURPLE_TERRACOTTA,
		Material.MAGENTA_TERRACOTTA,
		Material.PINK_TERRACOTTA,
		Material.WHITE_GLAZED_TERRACOTTA,
		Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
		Material.GRAY_GLAZED_TERRACOTTA,
		Material.BLACK_GLAZED_TERRACOTTA,
		Material.RED_GLAZED_TERRACOTTA,
		Material.ORANGE_GLAZED_TERRACOTTA,
		Material.YELLOW_GLAZED_TERRACOTTA,
		Material.LIGHT_BLUE_GLAZED_TERRACOTTA,
		Material.BLUE_GLAZED_TERRACOTTA,
		Material.PURPLE_GLAZED_TERRACOTTA,
		Material.MAGENTA_GLAZED_TERRACOTTA,
		Material.PINK_GLAZED_TERRACOTTA,
	)),
	COLORED_CONCRETE(listOf(
		Material.WHITE_CONCRETE,
		Material.LIGHT_GRAY_CONCRETE,
		Material.GRAY_CONCRETE,
		Material.BLACK_CONCRETE,
		Material.RED_CONCRETE,
		Material.ORANGE_CONCRETE,
		Material.YELLOW_CONCRETE,
		Material.LIGHT_BLUE_CONCRETE,
		Material.BLUE_CONCRETE,
		Material.PURPLE_CONCRETE,
		Material.MAGENTA_CONCRETE,
		Material.PINK_CONCRETE,
		Material.WHITE_CONCRETE_POWDER,
		Material.LIGHT_GRAY_CONCRETE_POWDER,
		Material.GRAY_CONCRETE_POWDER,
		Material.BLACK_CONCRETE_POWDER,
		Material.RED_CONCRETE_POWDER,
		Material.ORANGE_CONCRETE_POWDER,
		Material.YELLOW_CONCRETE_POWDER,
		Material.LIGHT_BLUE_CONCRETE_POWDER,
		Material.BLUE_CONCRETE_POWDER,
		Material.PURPLE_CONCRETE_POWDER,
		Material.MAGENTA_CONCRETE_POWDER,
		Material.PINK_CONCRETE_POWDER,
	)),
	COLORED_GLASS(listOf(
		Material.WHITE_STAINED_GLASS,
		Material.LIGHT_GRAY_STAINED_GLASS,
		Material.GRAY_STAINED_GLASS,
		Material.BLACK_STAINED_GLASS,
		Material.RED_STAINED_GLASS,
		Material.ORANGE_STAINED_GLASS,
		Material.YELLOW_STAINED_GLASS,
		Material.LIGHT_BLUE_STAINED_GLASS,
		Material.BLUE_STAINED_GLASS,
		Material.PURPLE_STAINED_GLASS,
		Material.MAGENTA_STAINED_GLASS,
		Material.PINK_STAINED_GLASS,
		Material.WHITE_STAINED_GLASS_PANE,
		Material.LIGHT_GRAY_STAINED_GLASS_PANE,
		Material.GRAY_STAINED_GLASS_PANE,
		Material.BLACK_STAINED_GLASS_PANE,
		Material.RED_STAINED_GLASS_PANE,
		Material.ORANGE_STAINED_GLASS_PANE,
		Material.YELLOW_STAINED_GLASS_PANE,
		Material.LIGHT_BLUE_STAINED_GLASS_PANE,
		Material.BLUE_STAINED_GLASS_PANE,
		Material.PURPLE_STAINED_GLASS_PANE,
		Material.MAGENTA_STAINED_GLASS_PANE,
		Material.PINK_STAINED_GLASS_PANE,
	)),
	DYE(listOf(
		Material.WHITE_DYE,
		Material.LIGHT_GRAY_DYE,
		Material.GRAY_DYE,
		Material.BLACK_DYE,
		Material.RED_DYE,
		Material.ORANGE_DYE,
		Material.YELLOW_DYE,
		Material.LIGHT_BLUE_DYE,
		Material.BLUE_DYE,
		Material.PURPLE_DYE,
		Material.MAGENTA_DYE,
		Material.PINK_DYE,
	)),
	LEATHER(listOf(
		Material.BOOK,
		Material.WRITABLE_BOOK,
		Material.WRITTEN_BOOK,
		Material.ITEM_FRAME,
		Material.LEATHER_HORSE_ARMOR,
		Material.LEATHER_BOOTS,
		Material.LEATHER_LEGGINGS,
		Material.LEATHER_CHESTPLATE,
		Material.LEATHER_HELMET,
		Material.BOOKSHELF,
		Material.LECTERN
	)),
	BASIC_TOOL(listOf(
		Material.WOODEN_AXE,
		Material.WOODEN_HOE,
		Material.WOODEN_SWORD,
		Material.WOODEN_SHOVEL,
		Material.WOODEN_PICKAXE,
		Material.STONE_AXE,
		Material.STONE_HOE,
		Material.STONE_SWORD,
		Material.STONE_SHOVEL,
		Material.STONE_PICKAXE,
	)),
	IRON(listOf(
		Material.IRON_INGOT,
		Material.IRON_BLOCK,
		Material.IRON_BARS,
		Material.CHAIN,
		Material.IRON_DOOR,
		Material.IRON_TRAPDOOR,
		Material.RAW_IRON_BLOCK,
		Material.IRON_SHOVEL,
		Material.IRON_AXE,
		Material.IRON_PICKAXE,
		Material.IRON_HOE,
		Material.IRON_SWORD,
		Material.IRON_HELMET,
		Material.IRON_CHESTPLATE,
		Material.IRON_LEGGINGS,
		Material.IRON_BOOTS,
		Material.HEAVY_WEIGHTED_PRESSURE_PLATE,
		Material.BUCKET,
		Material.LAVA_BUCKET,
		Material.MINECART,
		Material.CAULDRON,
		Material.HOPPER,
		Material.CHEST_MINECART,
		Material.HOPPER_MINECART,
		Material.RAIL
	)),
	OAK_WOOD(listOf(
		Material.OAK_WOOD,
		Material.STRIPPED_OAK_LOG,
		Material.STRIPPED_OAK_WOOD,
		Material.OAK_STAIRS,
		Material.OAK_SLAB,
		Material.OAK_FENCE,
		Material.OAK_FENCE_GATE,
		Material.OAK_DOOR,
		Material.OAK_TRAPDOOR,
		Material.OAK_PRESSURE_PLATE,
		Material.OAK_BUTTON,
		Material.OAK_BOAT,
		Material.OAK_CHEST_BOAT,
	)),
	BIRCH_WOOD(listOf(
		Material.BIRCH_WOOD,
		Material.STRIPPED_BIRCH_LOG,
		Material.STRIPPED_BIRCH_WOOD,
		Material.BIRCH_STAIRS,
		Material.BIRCH_SLAB,
		Material.BIRCH_FENCE,
		Material.BIRCH_FENCE_GATE,
		Material.BIRCH_DOOR,
		Material.BIRCH_TRAPDOOR,
		Material.BIRCH_PRESSURE_PLATE,
		Material.BIRCH_BUTTON,
		Material.BIRCH_BOAT,
		Material.BIRCH_CHEST_BOAT,
	)),
	OTHER_WOOD(listOf(
		Material.STICK,
		Material.CRAFTING_TABLE,
		Material.CHEST,
		Material.BARREL,
		Material.BOWL,
		Material.COMPOSTER,
		Material.CHISELED_BOOKSHELF,
	)),
	ANIMAL_PRODUCT(listOf(
		Material.MUTTON,
		Material.COOKED_MUTTON,
		Material.EGG,
		Material.CHICKEN,
		Material.COOKED_CHICKEN,
		Material.PORKCHOP,
		Material.COOKED_PORKCHOP,
		Material.BEEF,
		Material.COOKED_BEEF,
		Material.FEATHER,
		Material.MILK_BUCKET,
		Material.SALMON,
		Material.COOKED_SALMON,
		Material.SALMON_BUCKET,
		Material.COD,
		Material.COOKED_COD,
		Material.COD_BUCKET,
		Material.INK_SAC
	)),
	FARMING(listOf(
		Material.WHEAT_SEEDS,
		Material.WHEAT,
		Material.BREAD,
		Material.HAY_BLOCK,
	)),
	SEA(listOf(
		Material.WATER_BUCKET,
		Material.SEAGRASS,
		Material.KELP,
		Material.DRIED_KELP,
		Material.DRIED_KELP_BLOCK,
	)),
	COAL(listOf(
		Material.COAL,
		Material.CHARCOAL,
		Material.TORCH,
		Material.COAL_BLOCK,
		Material.LANTERN
	)),
	EXPLOSIVE(listOf(
		Material.TNT,
		Material.TNT_MINECART,
		Material.SUGAR_CANE,
		Material.PAPER,
		Material.GUNPOWDER,
		Material.SUGAR,
		Material.FIREWORK_STAR,
		Material.FIREWORK_ROCKET
	)),
	HOSTILE_MOBS(listOf(
		Material.ROTTEN_FLESH,
		Material.SPIDER_EYE,
		Material.BONE,
		Material.BONE_MEAL,
		Material.BONE_BLOCK,
		Material.STRING,
		Material.ARROW,
		Material.FERMENTED_SPIDER_EYE,
		Material.ENDER_PEARL,
	)),
	PLANT(listOf(
		Material.RED_MUSHROOM,
		Material.BROWN_MUSHROOM,
		Material.OAK_LEAVES,
		Material.BIRCH_LEAVES,
		Material.SHORT_GRASS,
		Material.OAK_SAPLING,
		Material.BIRCH_SAPLING,
		Material.PUMPKIN,
		Material.PUMPKIN_SEEDS,
		Material.JACK_O_LANTERN,
		Material.CARVED_PUMPKIN,
		Material.APPLE,
		Material.VINE,
		Material.GLOW_LICHEN,
	)),
	FLOWER(listOf(
		Material.ROSE_BUSH,
		Material.DANDELION,
		Material.PEONY,
		Material.LILAC,
		Material.CORNFLOWER,
		Material.LILY_OF_THE_VALLEY,
		Material.OXEYE_DAISY,
		Material.POPPY,
		Material.AZURE_BLUET,
	)),
	WORKSTATION(listOf(
		Material.FURNACE,
		Material.FURNACE_MINECART,
		Material.LOOM,
		Material.CARTOGRAPHY_TABLE,
		Material.SMITHING_TABLE,
		Material.FLETCHING_TABLE,
		Material.GRINDSTONE,
		Material.SMOKER,
		Material.BLAST_FURNACE,
		Material.STONECUTTER
	)),
	COPPER(listOf(
		Material.RAW_COPPER,
		Material.COPPER_INGOT,
		Material.RAW_COPPER_BLOCK,
		Material.LIGHTNING_ROD,
		Material.COPPER_BLOCK,
		Material.CUT_COPPER,
		Material.CUT_COPPER_SLAB,
		Material.CUT_COPPER_STAIRS,
	)),
	STONE_VARIANT(listOf(
		Material.ANDESITE,
		Material.ANDESITE_SLAB,
		Material.ANDESITE_WALL,
		Material.ANDESITE_STAIRS,
		Material.POLISHED_ANDESITE,
		Material.POLISHED_ANDESITE_SLAB,
		Material.POLISHED_ANDESITE_STAIRS,
		Material.DIORITE,
		Material.DIORITE_SLAB,
		Material.DIORITE_WALL,
		Material.DIORITE_STAIRS,
		Material.POLISHED_DIORITE,
		Material.POLISHED_DIORITE_SLAB,
		Material.POLISHED_DIORITE_STAIRS,
		Material.GRANITE,
		Material.GRANITE_SLAB,
		Material.GRANITE_WALL,
		Material.GRANITE_STAIRS,
		Material.POLISHED_GRANITE,
		Material.POLISHED_GRANITE_SLAB,
		Material.POLISHED_GRANITE_STAIRS,
	)),
	CLAY(listOf(
		Material.CLAY,
		Material.CLAY_BALL,
		Material.BRICK,
		Material.BRICKS,
		Material.BRICK_SLAB,
		Material.BRICK_WALL,
		Material.BRICK_STAIRS,
		Material.FLOWER_POT,
		Material.DECORATED_POT,
		Material.TERRACOTTA,
	)),
	HARD_FOOD(listOf(
		Material.CAKE,
		Material.PUMPKIN_PIE,
		Material.SUSPICIOUS_STEW,
		Material.CARROT,
		Material.POTATO,
		Material.MUSHROOM_STEW,
	)),
	SAND(listOf(
		Material.SAND,
		Material.GLASS,
		Material.GLASS_PANE,
		Material.GLASS_BOTTLE,
		Material.POTION,
		Material.SANDSTONE,
		Material.SANDSTONE_SLAB,
		Material.SANDSTONE_WALL,
		Material.SANDSTONE_STAIRS,
		Material.SMOOTH_SANDSTONE,
		Material.SMOOTH_SANDSTONE_SLAB,
		Material.SMOOTH_SANDSTONE_STAIRS,
		Material.CUT_SANDSTONE,
		Material.CUT_SANDSTONE_SLAB,
		Material.CHISELED_SANDSTONE
	)),
	STONE(listOf(
		Material.COBBLESTONE,
		Material.STONE,
		Material.STONE_STAIRS,
		Material.STONE_SLAB,
		Material.STONE_PRESSURE_PLATE,
		Material.STONE_BUTTON,
		Material.COBBLESTONE_SLAB,
		Material.COBBLESTONE_STAIRS,
		Material.COBBLESTONE_WALL,
		Material.STONE_BRICKS,
		Material.CRACKED_STONE_BRICKS,
		Material.STONE_BRICK_STAIRS,
		Material.STONE_BRICK_SLAB,
		Material.STONE_BRICK_WALL,
		Material.CHISELED_STONE_BRICKS,
		Material.SMOOTH_STONE,
		Material.SMOOTH_STONE_SLAB,
	)),
	WEIRD_TOOL(listOf(
		Material.FLINT,
		Material.FLINT_AND_STEEL,
		Material.BOW,
		Material.CROSSBOW,
		Material.FISHING_ROD,
		Material.TRIPWIRE_HOOK,
		Material.LEVER,
		Material.ARMOR_STAND,
		Material.PAINTING,
		Material.SHEARS,
		Material.SHIELD,
	)),
	DIRT(listOf(
		Material.DIRT,
		Material.COARSE_DIRT,
		Material.GRAVEL,
		Material.MUD,
		Material.PACKED_MUD,
		Material.MUD_BRICKS,
		Material.MUD_BRICK_STAIRS,
		Material.MUD_BRICK_SLAB,
		Material.MUD_BRICK_WALL,
	))
}