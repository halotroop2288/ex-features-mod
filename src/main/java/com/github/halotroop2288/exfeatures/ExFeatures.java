package com.github.halotroop2288.exfeatures;

import com.github.halotroop2288.exfeatures.registries.BlockRegistry;
import com.github.halotroop2288.exfeatures.registries.EntityRegistry;
import com.github.halotroop2288.exfeatures.registries.ItemRegistry;

import net.fabricmc.api.ModInitializer;

/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details. */
public class ExFeatures implements ModInitializer
{

	@Override
	public void onInitialize()
	{
		EntityRegistry.registerEntities();
		EntityRegistry.registerEntitySpawns();
		BlockRegistry.registerBlocks();
		ItemRegistry.registerItems();
		ExFeaturesSounds.registerSounds();
		System.out.println("Ex-Features loaded.");
	}
}
