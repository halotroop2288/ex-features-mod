package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;

@Mixin(InGameHud.class)
public class InGameHUDMixin extends DrawableHelper
{
	@Shadow MinecraftClient client;
	
	@Inject(at = @At("TAIL"), method = "render", remap = false)
	public void render(CallbackInfo info)
	{
		if (!this.client.options.debugEnabled)
			this.client.textRenderer.draw("Minecraft " + SharedConstants.getGameVersion().getName(), 1.55F, 2F, 14737632); // CLOSE ENOUGH!
	}
}
