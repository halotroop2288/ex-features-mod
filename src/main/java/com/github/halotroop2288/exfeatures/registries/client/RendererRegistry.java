package com.github.halotroop2288.exfeatures.registries.client;

import com.github.halotroop2288.exfeatures.entities.SeatEntity;
import com.github.halotroop2288.exfeatures.entities.render.*;
import com.github.halotroop2288.exfeatures.entities.render.model.*;
import com.github.halotroop2288.exfeatures.registries.EntityRegistry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class RendererRegistry
{
	public static void registerEntityRenderers()
	{
		EntityRendererRegistry.INSTANCE
			.register(EntityRegistry.STEVE_VILLAGER, (entityRenderDispatcher, context) -> new SteveVillagerRenderer
				(entityRenderDispatcher, new SteveVillagerModel(1, 64, 64), 1));
		EntityRendererRegistry.INSTANCE
			.register(EntityRegistry.PIGMAN, (entityRenderDispatcher, context) -> new PigmanRenderer
				(entityRenderDispatcher, new PigmanModel(1), 1));
		EntityRendererRegistry.INSTANCE
			.register(EntityRegistry.SEAT, (entityRenderDispatcher, context) -> new InvisibleEntityRenderer<SeatEntity>
		(entityRenderDispatcher));
	}
}
