package com.github.halotroop2288.exfeatures;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome.SpawnEntry;
import net.minecraft.world.biome.Biomes;

public class ExFeatures implements ModInitializer
{
	public static SpawnEntry GIANT_ZOMBIE_SPAWN = new SpawnEntry(EntityType.GIANT, 1, 0, 2);
	public static SpawnEntry ZOMBIE_HORSE_SPAWN = new SpawnEntry(EntityType.ZOMBIE_HORSE, 2, 0, 5);

	@Override
	public void onInitialize()
	{
		ItemAndBlockRegistries.registerBlocks();
		ItemAndBlockRegistries.registerItems();
		Biomes.DESERT.getEntitySpawnList(EntityCategory.MONSTER).add(GIANT_ZOMBIE_SPAWN);
		Biomes.PLAINS.getEntitySpawnList(EntityCategory.MONSTER).add(GIANT_ZOMBIE_SPAWN);
		Biomes.DEFAULT.getEntitySpawnList(EntityCategory.MONSTER).add(ZOMBIE_HORSE_SPAWN);
		Biomes.BADLANDS.getEntitySpawnList(EntityCategory.MONSTER).add(ZOMBIE_HORSE_SPAWN);
		ExFeaturesSounds.registerSounds();
		System.out.println("Ex-Features loaded.");
	}
}
