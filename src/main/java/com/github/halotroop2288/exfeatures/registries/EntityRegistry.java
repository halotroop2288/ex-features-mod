package com.github.halotroop2288.exfeatures.registries;

import com.github.halotroop2288.exfeatures.entities.PigmanEntity;
import com.github.halotroop2288.exfeatures.entities.SeatEntity;
import com.github.halotroop2288.exfeatures.entities.SteveVillagerEntity;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome.SpawnEntry;

public class EntityRegistry
{
	public static final SpawnEntry GIANT_ZOMBIE_SPAWN = new SpawnEntry(EntityType.GIANT, 1, 0, 2);
	public static final SpawnEntry ZOMBIE_HORSE_SPAWN = new SpawnEntry(EntityType.ZOMBIE_HORSE, 2, 0, 5);
	public static final EntityType<SteveVillagerEntity> STEVE_VILLAGER = FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, SteveVillagerEntity::new)
		.size(EntityDimensions.fixed(1, 2)).build();
	public static final EntityType<PigmanEntity> PIGMAN = FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, PigmanEntity::new)
		.size(EntityDimensions.fixed(1, 2)).build();
	public static final EntityType<SeatEntity> SEAT = FabricEntityTypeBuilder.create(EntityCategory.MISC, SeatEntity::new).build();
	
	public static void registerEntities()
	{
		registerEntity("seat", SEAT);
		registerEntity("pigman", PIGMAN);
		registerEntity("steve_villager", STEVE_VILLAGER);
	}
	
	private static void registerEntity(String name, EntityType<?> entity)
	{ Registry.register(Registry.ENTITY_TYPE, new Identifier("exfeatures", name), entity); }
	
	public static void registerEntitySpawns()
	{
		Biomes.DESERT.getEntitySpawnList(EntityCategory.MONSTER).add(GIANT_ZOMBIE_SPAWN);
		Biomes.PLAINS.getEntitySpawnList(EntityCategory.MONSTER).add(GIANT_ZOMBIE_SPAWN);
		Biomes.DEFAULT.getEntitySpawnList(EntityCategory.MONSTER).add(ZOMBIE_HORSE_SPAWN);
		Biomes.BADLANDS.getEntitySpawnList(EntityCategory.MONSTER).add(ZOMBIE_HORSE_SPAWN);
	}
}
