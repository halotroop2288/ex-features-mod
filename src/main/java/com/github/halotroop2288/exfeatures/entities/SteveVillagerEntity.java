package com.github.halotroop2288.exfeatures.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class SteveVillagerEntity extends VillagerEntity
{
	public SteveVillagerEntity(EntityType<SteveVillagerEntity> entityType, World world)
	{ super(entityType, world); }
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		if (random.nextInt(2) == 1)
			return SoundEvents.ENTITY_PLAYER_BURP;
		else
			return SoundEvents.ENTITY_PLAYER_BREATH;
	}

	@Override
	protected SoundEvent getDeathSound()
	{ return SoundEvents.ENTITY_PLAYER_DEATH; }

	@Override
	public SoundEvent getEatSound(ItemStack stack)
	{ return SoundEvents.ENTITY_GENERIC_EAT; }

	@Override
	protected SoundEvent getDrinkSound(ItemStack stack)
	{
		if (stack.getItem() == Items.MILK_BUCKET)
			return SoundEvents.ENTITY_WANDERING_TRADER_DRINK_MILK;
		else if (stack.getItem() == Items.POTION)
			return SoundEvents.ENTITY_WANDERING_TRADER_DRINK_POTION;
		else return SoundEvents.ENTITY_GENERIC_DRINK;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		if (source == DamageSource.ON_FIRE)
			return SoundEvents.ENTITY_PLAYER_HURT_ON_FIRE;
		else if (source == DamageSource.DROWN)
			return SoundEvents.ENTITY_PLAYER_HURT_DROWN;
		else if (source == DamageSource.SWEET_BERRY_BUSH)
			return SoundEvents.ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH;
		else return SoundEvents.ENTITY_PLAYER_HURT;
	}
}
