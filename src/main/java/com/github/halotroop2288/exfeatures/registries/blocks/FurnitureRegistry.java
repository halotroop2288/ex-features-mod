package com.github.halotroop2288.exfeatures.registries.blocks;

import com.github.halotroop2288.exfeatures.blocks.ChairBlock;
import com.github.halotroop2288.exfeatures.blocks.TableBlock;
import com.github.halotroop2288.exfeatures.registries.BlockRegistry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;

public class FurnitureRegistry
{
	Block OAK_CHAIR;
	Block OAK_TABLE;
	Block BIRCH_CHAIR;
	Block BIRCH_TABLE;
	Block JUNGLE_CHAIR;
	Block JUNGLE_TABLE;
	Block SPRUCE_CHAIR;
	Block SPRUCE_TABLE;
	Block ACACIA_CHAIR;
	Block ACACIA_TABLE;
	Block DARK_OAK_CHAIR;
	Block DARK_OAK_TABLE;
	
	public static void registerFurniturePieces()
	{
		registerTableAndChair("oak", Blocks.OAK_PLANKS);
		registerTableAndChair("birch", Blocks.BIRCH_PLANKS);
		registerTableAndChair("jungle", Blocks.JUNGLE_PLANKS);
		registerTableAndChair("spruce", Blocks.SPRUCE_PLANKS);
		registerTableAndChair("acacia", Blocks.ACACIA_PLANKS);
		registerTableAndChair("dark_oak", Blocks.DARK_OAK_PLANKS);
	}
	
	public static void registerTableAndChair(String typeName, Block blockToCopySetttingsFrom)
	{
		BlockRegistry.registerBlock(typeName + "_table", new TableBlock(FabricBlockSettings.copy(blockToCopySetttingsFrom).build()), ItemGroup.DECORATIONS);
		BlockRegistry.registerBlock(typeName + "_chair", new ChairBlock(FabricBlockSettings.copy(blockToCopySetttingsFrom).build()), ItemGroup.DECORATIONS);
	}
}
