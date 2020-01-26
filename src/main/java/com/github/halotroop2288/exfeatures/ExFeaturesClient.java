package com.github.halotroop2288.exfeatures;

import com.github.halotroop2288.exfeatures.registries.blocks.FlowerRegistry;
import com.github.halotroop2288.exfeatures.registries.client.RendererRegistry;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Quaternion;

@Environment(EnvType.CLIENT)
public class ExFeaturesClient implements ClientModInitializer
{
	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient()
	{ // @formatter:off
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
		FlowerRegistry.CYAN_FLOWER,
		FlowerRegistry.PAEONIA,
		FlowerRegistry.ROSE);
		RendererRegistry.registerEntityRenderers();
	}

	@Environment(EnvType.CLIENT)
	public static void drawPlayerPreview(int x, int y, float size, LivingEntity entity) // FIXME: Limit yaw rotation to 180 degrees
	{
		RenderSystem.pushMatrix();
		RenderSystem.translatef(x, y, 1050.0F);
		RenderSystem.scalef(1.0F, 1.0F, -1.0F);
		MatrixStack matrixStack = new MatrixStack();
		matrixStack.translate(0.0D, 0.0D, 1000.0D);
		matrixStack.scale(size, size, size);
		Quaternion quaternion = Vector3f.POSITIVE_Z.getDegreesQuaternion(180.0F);
		Quaternion quaternion2 = Vector3f.POSITIVE_X.getDegreesQuaternion(0);
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
	
	@Environment(EnvType.CLIENT)
	public static void drawPlayerPreview(int x, int y, float mouseX, float size, LivingEntity entity) // FIXME: Limit yaw rotation to 180 degrees
	{
		MinecraftClient client = MinecraftClient.getInstance();
		RenderSystem.pushMatrix();
		RenderSystem.translatef(x, y, 1050.0F);
		RenderSystem.scalef(1.0F, 1.0F, -1.0F);
		MatrixStack matrixStack = new MatrixStack();
		matrixStack.translate(0.0D, 0.0D, 1000.0D);
		matrixStack.scale(size, size, size);
		Quaternion quaternion = Vector3f.POSITIVE_Z.getDegreesQuaternion(180.0F);
		Quaternion quaternion2 = Vector3f.POSITIVE_X.getDegreesQuaternion(0);
		quaternion.hamiltonProduct(quaternion2);
		matrixStack.multiply(quaternion);
		EntityRenderDispatcher entityRenderDispatcher = client.getEntityRenderManager();
		quaternion2.conjugate();
		entityRenderDispatcher.setRotation(quaternion2);
		entityRenderDispatcher.setRenderShadows(false);
		VertexConsumerProvider.Immediate immediate = client.getBufferBuilders().getEntityVertexConsumers();
		entityRenderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixStack, immediate, 15728880);
		immediate.draw();
		entityRenderDispatcher.setRenderShadows(true);
		RenderSystem.popMatrix();
	}
}
