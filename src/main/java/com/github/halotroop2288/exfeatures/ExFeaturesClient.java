package com.github.halotroop2288.exfeatures;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ExFeaturesClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
		ItemAndBlockRegistries.CYAN_FLOWER,
		ItemAndBlockRegistries.PAEONIA,
		ItemAndBlockRegistries.ROSE);
	}
}
