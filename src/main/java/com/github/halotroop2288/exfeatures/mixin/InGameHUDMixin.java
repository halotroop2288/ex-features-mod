package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

@Mixin(InGameHud.class)
public class InGameHUDMixin extends DrawableHelper
{
	@Shadow
	MinecraftClient client;

	@Inject(at = @At("TAIL"), method = "render", remap = false)
	public void render(CallbackInfo info)
	{
		if (!this.client.options.debugEnabled)
			this.client.textRenderer.draw("Minecraft " + SharedConstants.getGameVersion().getName(), 1.55F, 2F, 14737632); // CLOSE ENOUGH!
		if ((this.client.player.isSprinting() || this.client.player.isFallFlying() || this.client.player.isSneaking()
			|| this.client.player.isClimbing() || this.client.player.isSwimming() || this.client.player.abilities.flying)
			&& this.client.options.perspective == 0 && !this.client.player.isInvisible() && this.client.currentScreen == null)
			InventoryScreen.drawEntity(25, 50, 20,
				this.client.player.getYaw(client.getTickDelta()),
				-this.client.player.pitch, this.client.player);
	}
}
