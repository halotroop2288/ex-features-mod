package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.halotroop2288.exfeatures.ExFeatures;
import com.github.halotroop2288.exfeatures.gui.container.CustomPlayerInventoryScreen;
import com.github.halotroop2288.exfeatures.gui.menu.ClassicTitleScreen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

@Mixin(MinecraftClient.class)
public class MenuReplacerMixin
{
	private MinecraftClient client = ((MinecraftClient)(Object)this);
	@Inject(method = "openScreen", at = @At("TAIL"))
	private void overrideScreen(CallbackInfo info)
	{
		if (ExFeatures.config.useCustomTitleScreen() && client.currentScreen instanceof TitleScreen)
		{
			client.openScreen(new ClassicTitleScreen());
		}
		if (ExFeatures.config.useCustomInventoryScreen() && client.currentScreen instanceof InventoryScreen)
		{
			client.openScreen(new CustomPlayerInventoryScreen(client.player));
		}
	}
}
