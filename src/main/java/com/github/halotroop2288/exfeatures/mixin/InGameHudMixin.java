package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.halotroop2288.exfeatures.ExFeatures;
import com.github.halotroop2288.exfeatures.ExFeaturesClient;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper
{
	private MinecraftClient client = MinecraftClient.getInstance();
	private Screen currentScreen;
	private PlayerEntity player;

	@Inject(at = @At("TAIL"), method = "render")
	public void render(CallbackInfo info)
	{
		this.player = this.client.player;
		this.currentScreen = this.client.currentScreen;
		if (!this.client.options.debugEnabled)
		{
			if (((this.client.player.isSprinting() || this.player.isFallFlying() || this.player.isSneaking()
				|| this.player.isClimbing() || this.player.isSwimming() || this.player.abilities.flying)
				&& this.client.options.perspective == 0 && !this.player.isInvisible() && MinecraftClient.isHudEnabled() && currentScreen == null)
				|| (currentScreen instanceof GameMenuScreen) && ((GameMenuScreen) (currentScreen)).isPauseScreen())
				ExFeaturesClient.drawPlayerPreview(25, 50, 20, this.player);
			if (ExFeatures.config.showVersionStringInHud())
				this.client.textRenderer.draw("Minecraft " + SharedConstants.getGameVersion().getName(), 1.55F, 2F, 14737632); // CLOSE ENOUGH!
		}
	}
}
