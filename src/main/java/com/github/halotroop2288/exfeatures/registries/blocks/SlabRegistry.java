package com.github.halotroop2288.exfeatures.registries.blocks;

import com.github.halotroop2288.exfeatures.registries.BlockRegistry;

import net.minecraft.block.*;
import net.minecraft.item.*;

public class SlabRegistry
{
	public static Block DIRT_SLAB = new SlabBlock(Block.Settings.copy(Blocks.DIRT));
	public static Block GRASS_SLAB = new SlabBlock(Block.Settings.copy(Blocks.GRASS_BLOCK)); // FIXME: See the Trello board for a list of bugs caused by this block
	public static Block COARSE_DIRT_SLAB = new SlabBlock(Block.Settings.copy(Blocks.COARSE_DIRT));
	public static Block MYCELIUM_SLAB = new SlabBlock(Block.Settings.copy(Blocks.MYCELIUM));
	public static Block PODZOL_SLAB = new SlabBlock(Block.Settings.copy(Blocks.PODZOL));
	
	public static void registerSlabs()
	{
		registerSlab("dirt_slab", DIRT_SLAB);
		registerSlab("grass_slab", GRASS_SLAB, (ItemGroup) null);
		registerSlab("coarse_dirt_slab", COARSE_DIRT_SLAB);
		registerSlab("mycelium_slab", MYCELIUM_SLAB);
		registerSlab("podzol_slab", PODZOL_SLAB);
	}
	
	private static void registerSlab(String id, Block block, ItemGroup group)
	{
		BlockRegistry.registerBlock(id, block, group);
	}
	
	private static void registerSlab(String id, Block block)
	{
		BlockRegistry.registerBlock(id, block, ItemGroup.BUILDING_BLOCKS);
	}
}
