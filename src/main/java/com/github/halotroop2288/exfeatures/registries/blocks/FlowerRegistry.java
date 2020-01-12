package com.github.halotroop2288.exfeatures.registries.blocks;

import com.github.halotroop2288.exfeatures.registries.BlockRegistry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;

public class FlowerRegistry
{
	public static Block PAEONIA = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.POPPY).build());
	public static Block ROSE = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.POPPY).build());
	public static Block CYAN_FLOWER = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.POPPY).build());
	
	public static void registerFlowers()
	{
		registerFlower("paeonia", PAEONIA);
		registerFlower("rose", ROSE);
		registerFlower("cyan_flower", CYAN_FLOWER);
	}
	
	private static void registerFlower(String id, Block block)
	{
		BlockRegistry.registerBlock(id, block, ItemGroup.DECORATIONS);
	}
}
