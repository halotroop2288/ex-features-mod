package com.github.halotroop2288.exfeatures.registries.blocks;

import com.github.halotroop2288.exfeatures.registries.BlockRegistry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;

public class FlowerRegistry
{
	public static Block PAEONIA = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.PEONY).build());
	public static Item PAEONIA_ITEM = new BlockItem(PAEONIA, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static Block ROSE = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.POPPY).build());
	public static Item ROSE_ITEM = new BlockItem(ROSE, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static Block CYAN_FLOWER = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.POPPY).build());
	public static Item CYAN_FLOWER_ITEM = new BlockItem(CYAN_FLOWER, new Item.Settings().group(ItemGroup.DECORATIONS));
	
	public static void registerFlowers()
	{
		BlockRegistry.registerBlock("paeonia", PAEONIA, PAEONIA_ITEM);
		BlockRegistry.registerBlock("rose", ROSE, ROSE_ITEM);
		BlockRegistry.registerBlock("cyan_flower", CYAN_FLOWER, CYAN_FLOWER_ITEM);
	}
}
