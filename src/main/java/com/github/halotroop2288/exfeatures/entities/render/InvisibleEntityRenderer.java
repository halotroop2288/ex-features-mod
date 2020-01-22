package com.github.halotroop2288.exfeatures.entities.render;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class InvisibleEntityRenderer<T extends Entity> extends EntityRenderer<T>
{
	public InvisibleEntityRenderer(EntityRenderDispatcher entityRenderDispatcher)
	{ super(entityRenderDispatcher); }

	@Override
	public Identifier getTexture(T entity)
	{ return null; }
}
