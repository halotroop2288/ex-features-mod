package com.github.halotroop2288.exfeatures;

import com.github.halotroop2288.exfeatures.entities.SteveVillagerEntity;
import com.github.halotroop2288.exfeatures.entities.render.SteveVillagerRenderer;
import com.github.halotroop2288.exfeatures.entities.render.model.SteveVillagerModel;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome.SpawnEntry;
import net.minecraft.world.biome.Biomes;

public class ExFeatures implements ModInitializer
{
	public static final SpawnEntry GIANT_ZOMBIE_SPAWN = new SpawnEntry(EntityType.GIANT, 1, 0, 2);
	public static final SpawnEntry ZOMBIE_HORSE_SPAWN = new SpawnEntry(EntityType.ZOMBIE_HORSE, 2, 0, 5);
	public static final EntityType<SteveVillagerEntity> STEVE_VILLAGER = FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, SteveVillagerEntity::new)
		.size(EntityDimensions.fixed(1, 2)).build();

	@Override
	public void onInitialize()
	{
		Registry.register(Registry.ENTITY_TYPE, new Identifier("exfeatures", "steve_villager"), STEVE_VILLAGER);
		EntityRendererRegistry.INSTANCE.register(STEVE_VILLAGER,
			(entityRenderDispatcher, context) -> new SteveVillagerRenderer(entityRenderDispatcher, new SteveVillagerModel(1, 64, 64), 1));
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
