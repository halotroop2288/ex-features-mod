package com.github.halotroop2288.exfeatures.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class PlayerMobEntity extends MobEntity
{
	public PlayerMobEntity(EntityType<PlayerMobEntity> type, World world)
	{
		super(type, world);
	}
	
	@Override
	public void tick()
	{
		
	}
}
