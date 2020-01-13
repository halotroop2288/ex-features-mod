package com.github.halotroop2288.exfeatures.entities.render;

import com.github.halotroop2288.exfeatures.entities.PigmanEntity;
import com.github.halotroop2288.exfeatures.entities.render.model.PigmanModel;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class PigmanRenderer extends BipedEntityRenderer<PigmanEntity, PigmanModel>
{
	public PigmanRenderer(EntityRenderDispatcher renderManager, PigmanModel model, float shadowSize)
	{ super(renderManager, model, shadowSize); }

	@Override
	public Identifier getTexture(PigmanEntity entity)
	{ return new Identifier("exfeatures", "textures/entity/villager/pigman.png"); }
}
