package com.github.halotroop2288.exfeatures.entities.render;

import com.github.halotroop2288.exfeatures.entities.SteveVillagerEntity;
import com.github.halotroop2288.exfeatures.entities.render.model.SteveVillagerModel;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SteveVillagerRenderer extends MobEntityRenderer<SteveVillagerEntity, SteveVillagerModel>
{
	public SteveVillagerRenderer(EntityRenderDispatcher renderManager, SteveVillagerModel model, float shadowSize)
	{ super(renderManager, model, shadowSize); }

	@Override
	public Identifier getTexture(SteveVillagerEntity entity)
	{ return new Identifier("exfeatures", "textures/entity/villager/steve.png"); }
}
