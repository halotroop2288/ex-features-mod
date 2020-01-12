package com.github.halotroop2288.exfeatures.registries.blocks;

import com.github.halotroop2288.exfeatures.registries.BlockRegistry;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class SlabRegistry
{
	public static Block DIRT_SLAB = new SlabBlock(Block.Settings.copy(Blocks.DIRT));
	public static Item DIRT_SLAB_ITEM = new BlockItem(DIRT_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block GRASS_SLAB = new SlabBlock(Block.Settings.copy(Blocks.GRASS_BLOCK));
	public static Item GRASS_SLAB_ITEM = new BlockItem(GRASS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block COARSE_DIRT_SLAB = new SlabBlock(Block.Settings.copy(Blocks.COARSE_DIRT));
	public static Item COARSE_DIRT_SLAB_ITEM = new BlockItem(COARSE_DIRT_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block MYCELIUM_SLAB = new SlabBlock(Block.Settings.copy(Blocks.MYCELIUM));
	public static Item MYCELIUM_SLAB_ITEM = new BlockItem(MYCELIUM_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block PODZOL_SLAB = new SlabBlock(Block.Settings.copy(Blocks.PODZOL));
	public static Item PODZOL_SLAB_ITEM = new BlockItem(PODZOL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	
	public static void registerSlabs()
	{
		BlockRegistry.registerBlock("dirt_slab", DIRT_SLAB, DIRT_SLAB_ITEM);
		BlockRegistry.registerBlock("grass_slab", GRASS_SLAB, GRASS_SLAB_ITEM);
		BlockRegistry.registerBlock("coarse_dirt_slab", COARSE_DIRT_SLAB, COARSE_DIRT_SLAB_ITEM);
		BlockRegistry.registerBlock("mycelium_slab", MYCELIUM_SLAB, MYCELIUM_SLAB_ITEM);
		BlockRegistry.registerBlock("podzol_slab", PODZOL_SLAB, PODZOL_SLAB_ITEM);
	}
}
