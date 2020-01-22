package com.github.halotroop2288.exfeatures;

import com.github.halotroop2288.exfeatures.registries.blocks.FlowerRegistry;
import com.github.halotroop2288.exfeatures.registries.client.RendererRegistry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ExFeaturesClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{ // @formatter:off
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
		FlowerRegistry.CYAN_FLOWER,
		FlowerRegistry.PAEONIA,
		FlowerRegistry.ROSE);
		RendererRegistry.registerEntityRenderers();
	}
}
