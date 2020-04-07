package com.github.halotroop2288.exfeatures.registries.blocks;

import java.util.function.Consumer;

import com.github.halotroop2288.exfeatures.registries.BlockRegistry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

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
		forEveryBiome(FlowerRegistry::addFlowerFeature);
	}
	
	private static void registerFlower(String id, Block block)
	{
		BlockRegistry.registerBlock(id, block, ItemGroup.DECORATIONS);
	}

	private static void forEveryBiome(Consumer<Biome> biomes)
	{
		Registry.BIOME.forEach(biomes);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((rawId, id, biome) -> biomes.accept(biome));
	}
	
	private static void addFlowerFeature(Biome biome)
	{
		biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.FLOWER.configure(
			(new RandomPatchFeatureConfig.Builder((new WeightedBlockStateProvider())
				.addState(ROSE.getDefaultState(), 5)
				.addState(PAEONIA.getDefaultState(), 5)
				.addState(CYAN_FLOWER.getDefaultState(), 5),
				new SimpleBlockPlacer())).tries(64).build())
			);
	}
}
