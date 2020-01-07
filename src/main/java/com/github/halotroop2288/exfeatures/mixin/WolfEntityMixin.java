package com.github.halotroop2288.exfeatures.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.halotroop2288.exfeatures.ExFeaturesSounds;

import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

@Mixin(WolfEntity.class)
public class WolfEntityMixin
{
	@Inject(method = "getAmbientSound", at = @At("RETURN"), cancellable = true)
	private void addHowlSound(CallbackInfoReturnable<SoundEvent> callback)
	{
		if (callback.getReturnValue() == SoundEvents.ENTITY_WOLF_AMBIENT)
		{
			WolfEntity wolf = (WolfEntity) (Object) this;
			if (wolf.getEntityWorld().getTimeOfDay() >= 12000 && new Random().nextInt(3) == 1 && !wolf.isTamed())
				callback.setReturnValue(ExFeaturesSounds.ENTITY_WOLF_HOWL);
		}
	}
}
