package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.passive.DolphinEntity;

@Mixin(DolphinEntity.class)
public class DolphinEntityMixin
{
	@Inject(at = @At("HEAD"), method = "initGoals")
	private void initGoals(CallbackInfo info)
	{ System.out.println("Successfully injected Dolphin Entity."); }
}
