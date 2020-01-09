package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Quaternion;

@Mixin(InGameHud.class)
public class InGameHUDMixin extends DrawableHelper
{
	@Shadow
	private MinecraftClient client;
	private Screen currentScreen;
	private PlayerEntity player;
	
	@Inject(at = @At("TAIL"), method = "render", remap = false)
	public void render(CallbackInfo info)
	{
		this.player = this.client.player;
		this.currentScreen = this.client.currentScreen;
		if (!this.client.options.debugEnabled)
		{
			this.client.textRenderer.draw("Minecraft " + SharedConstants.getGameVersion().getName(), 1.55F, 2F, 14737632); // CLOSE ENOUGH!
			if ((this.client.player.isSprinting() || this.client.player.isFallFlying() || this.client.player.isSneaking()
				|| this.player.isClimbing() || this.client.player.isSwimming() || this.client.player.abilities.flying)
				&& this.client.options.perspective == 0 && !this.client.player.isInvisible() && MinecraftClient.isHudEnabled()
				&& (currentScreen == null /*|| currentScreen instanceof [F3+ESC Screen]*/))
				drawPlayerPreview(25, 50, 20);
		}
	}

	public void drawPlayerPreview(int x, int y, int size)
	{
		float mouseY = -this.client.player.pitch;
		LivingEntity entity = this.client.player;
		float g = (float) Math.atan((double) (mouseY / 40.0F));
		RenderSystem.pushMatrix();
		RenderSystem.translatef((float) x, (float) y, 1050.0F);
		RenderSystem.scalef(1.0F, 1.0F, -1.0F);
		MatrixStack matrixStack = new MatrixStack();
		matrixStack.translate(0.0D, 0.0D, 1000.0D);
		matrixStack.scale((float) size, (float) size, (float) size);
		Quaternion quaternion = Vector3f.POSITIVE_Z.getDegreesQuaternion(180.0F);
		Quaternion quaternion2 = Vector3f.POSITIVE_X.getDegreesQuaternion(g * 20.0F);
		quaternion.hamiltonProduct(quaternion2);
		matrixStack.multiply(quaternion);
		EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderManager();
		quaternion2.conjugate();
		entityRenderDispatcher.setRotation(quaternion2);
		entityRenderDispatcher.setRenderShadows(false);
		VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
		entityRenderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixStack, immediate, 15728880);
		immediate.draw();
		entityRenderDispatcher.setRenderShadows(true);
		RenderSystem.popMatrix();
	}
}
