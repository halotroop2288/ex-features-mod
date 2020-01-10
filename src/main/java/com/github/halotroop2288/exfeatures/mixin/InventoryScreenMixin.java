package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin
{
	@Shadow
	MinecraftClient client;

	@Inject(at = @At("HEAD"), method = "init")
	private void init(CallbackInfo info)
	{ System.out.println("Successfully injected the Inventory Screen."); }

	@Inject(at = @At("HEAD"), method = "render")
	private void render(CallbackInfo info)
	{
		// TODO: Add XP counter, level counter and player name to screen
	}
}
