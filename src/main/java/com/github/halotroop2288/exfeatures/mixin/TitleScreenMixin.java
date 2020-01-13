package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.halotroop2288.exfeatures.gui.ClassicTitleScreen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(MinecraftClient.class)
public class TitleScreenMixin
{
	MinecraftClient client = ((MinecraftClient)(Object)this);
	@Inject(method = "openScreen", at = @At("TAIL"))
	private void overrideTitleScreen(CallbackInfo info)
	{
		if (client.currentScreen instanceof TitleScreen)
			client.openScreen(new ClassicTitleScreen());
	}
}
