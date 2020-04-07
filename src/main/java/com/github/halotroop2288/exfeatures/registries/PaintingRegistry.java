package com.github.halotroop2288.exfeatures.registries;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PaintingRegistry
{
	public static void registerPaintings()
	{
		registerMotive("water", 32, 32);
		registerMotive("earth", 32, 32);
		registerMotive("fire", 32, 32);
		registerMotive("air", 32, 32);
	}
	
	// Just to make this needlessly complicated lmao
	private static void registerMotive(String id, int width, int height)
	{
		Registry.register(Registry.PAINTING_MOTIVE, new Identifier("exfeatures", id), new PaintingMotive(width, height));
	}
}
