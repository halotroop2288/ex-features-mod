package com.github.halotroop2288.exfeatures.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombiePigmanEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PigmanEntity extends VillagerEntity
{
	public PigmanEntity(EntityType<PigmanEntity> entityType, World world)
	{ super(entityType, world); }

	@Override
	protected SoundEvent getAmbientSound()
	{ return SoundEvents.ENTITY_PIG_AMBIENT; }

	@Override
	protected SoundEvent getDeathSound()
	{ return SoundEvents.ENTITY_PIG_DEATH; }

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{ return SoundEvents.ENTITY_PIG_HURT; }

	@Override
	public void onStruckByLightning(LightningEntity lightning)
	{
		ZombiePigmanEntity zombiePigmanEntity = (ZombiePigmanEntity) EntityType.ZOMBIE_PIGMAN.create(this.world);
		zombiePigmanEntity.setPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.yaw, this.pitch);
		zombiePigmanEntity.initialize(this.world, this.world.getLocalDifficulty(new BlockPos(zombiePigmanEntity)),
			SpawnType.CONVERSION, (EntityData) null, (CompoundTag) null);
		zombiePigmanEntity.setAiDisabled(this.isAiDisabled());
		if (this.hasCustomName())
		{
			zombiePigmanEntity.setCustomName(this.getCustomName());
			zombiePigmanEntity.setCustomNameVisible(this.isCustomNameVisible());
		}
		this.world.spawnEntity(zombiePigmanEntity);
		this.remove();
	}
}
