package com.github.halotroop2288.exfeatures.registries;

import com.github.halotroop2288.exfeatures.blocks.CryingObsidianBlock;
import com.github.halotroop2288.exfeatures.blocks.UnstableTNTBlock;
import com.github.halotroop2288.exfeatures.registries.blocks.FlowerRegistry;
import com.github.halotroop2288.exfeatures.registries.blocks.FurnitureRegistry;
import com.github.halotroop2288.exfeatures.registries.blocks.SlabRegistry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;

public class BlockRegistry
{
	public static Block NETHER_REACTOR_CORE = new Block(FabricBlockSettings.copy(Blocks.OBSIDIAN).build());
	public static Block RUBY_ORE = new OreBlock(FabricBlockSettings.copy(Blocks.EMERALD_ORE).build());
	public static Block UNSTABLE_TNT = new UnstableTNTBlock(FabricBlockSettings.copy(Blocks.TNT).build());
	public static Block CRYING_OBSIDIAN = new CryingObsidianBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).build());
	public static Block BOARD_CHALKBOARD = new SignBlock(FabricBlockSettings.copy(Blocks.BLACK_CONCRETE).build(), SignType.DARK_OAK); // TODO: This is a placeholder
	public static Block GLOWING_OBSIDIAN = new Block(Block.Settings.copy(Blocks.OBSIDIAN))
	{
		@Override
		public int getLuminance(BlockState state)
		{ return 9; }

		@Override
		public boolean hasEmissiveLighting(BlockState state)
		{ return true; }
	};

	public static void registerBlocks()
	{
		registerBlock("ruby_ore", RUBY_ORE);
		registerBlock("crying_obsidian", CRYING_OBSIDIAN);
		registerBlock("glowing_obsidian", GLOWING_OBSIDIAN);
		registerBlock("unstable_tnt", UNSTABLE_TNT, ItemGroup.REDSTONE);
		registerBlock("netherreactor", NETHER_REACTOR_CORE);
		SlabRegistry.registerSlabs();
		FlowerRegistry.registerFlowers();
		FurnitureRegistry.registerFurniturePieces();
	}

	public static void registerBlock(String id, Block block, Item blockItem)
	{
		Registry.register(Registry.BLOCK, new Identifier("exfeatures", id), block);
		ItemRegistry.registerItem(id, blockItem);
	}

	public static void registerBlock(String id, Block block, ItemGroup group)
	{
		Item blockItem = new BlockItem(block, new Item.Settings().group(group));
		registerBlock(id, block, blockItem);
	}

	public static void registerBlock(String id, Block block)
	{ registerBlock(id, block, ItemGroup.BUILDING_BLOCKS); }
}
