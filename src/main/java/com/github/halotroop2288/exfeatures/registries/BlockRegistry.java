package com.github.halotroop2288.exfeatures.registries;

import com.github.halotroop2288.exfeatures.blocks.*;
import com.github.halotroop2288.exfeatures.registries.blocks.*;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry
{
	public static Block UNSTABLE_TNT = new UnstableTNTBlock(FabricBlockSettings.copy(Blocks.TNT).build());
	public static Item UNSTABLE_TNT_ITEM = new BlockItem(UNSTABLE_TNT, new Item.Settings().group(ItemGroup.REDSTONE));
	public static Block RUBY_ORE = new OreBlock(FabricBlockSettings.copy(Blocks.EMERALD_ORE).build());
	public static Item RUBY_ORE_ITEM = new BlockItem(RUBY_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block CRYING_OBSIDIAN = new CryingObsidianBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).build());
	public static Item CRYING_OBSIDIAN_ITEM = new BlockItem(CRYING_OBSIDIAN, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block GLOWING_OBSIDIAN = new Block(Block.Settings.copy(Blocks.OBSIDIAN))
	{
		public int getLuminance(BlockState state)
		{ return 9; }

		public boolean hasEmissiveLighting(BlockState state)
		{ return true; }
	};
	public static Item GLOWING_OBSIDIAN_ITEM = new BlockItem(GLOWING_OBSIDIAN, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	
	public static void registerBlocks()
	{
		registerBlock("ruby_ore", RUBY_ORE, RUBY_ORE_ITEM);
		registerBlock("crying_obsidian", CRYING_OBSIDIAN, CRYING_OBSIDIAN_ITEM);
		registerBlock("glowing_obsidian", GLOWING_OBSIDIAN, GLOWING_OBSIDIAN_ITEM);
		registerBlock("unstable_tnt", UNSTABLE_TNT, UNSTABLE_TNT_ITEM);
		
		SlabRegistry.registerSlabs();
		FlowerRegistry.registerFlowers();
	}
	
	public static void registerBlock(String id, Block block, Item blockItem)
	{
		Registry.register(Registry.BLOCK, new Identifier("exfeatures", id), block);
		ItemRegistry.registerItem(id, blockItem);
	}
}
