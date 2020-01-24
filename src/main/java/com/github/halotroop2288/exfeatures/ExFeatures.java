package com.github.halotroop2288.exfeatures;

import org.aeonbits.owner.ConfigFactory;

import com.github.halotroop2288.exfeatures.config.ModConfig;
import com.github.halotroop2288.exfeatures.registries.BlockRegistry;
import com.github.halotroop2288.exfeatures.registries.EntityRegistry;
import com.github.halotroop2288.exfeatures.registries.ItemRegistry;
import com.github.halotroop2288.exfeatures.registries.SoundRegistry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://www.wtfpl.net/ for more details. */
public class ExFeatures implements ModInitializer
{
	public static final ModConfig config = ConfigFactory.create(ModConfig.class);
	public static final boolean useModMenu = FabricLoader.getInstance().isModLoaded("modmenu") && config.modmenuEnabled();
	public static final boolean useTrinkets = FabricLoader.getInstance().isModLoaded("trinkets") && config.trinketsEnabled();
	public static final boolean useIndev = FabricLoader.getInstance().isModLoaded("indevworldgen") && config.indevEnabled();
	
	@Override
	public void onInitialize()
	{
		SoundRegistry.registerSounds();
		EntityRegistry.registerEntities();
		EntityRegistry.registerEntitySpawns();
		//
		if (config.registerBlocks())
			BlockRegistry.registerBlocks();
		if (config.registerItems())
			ItemRegistry.registerItems();
		System.out.println("Ex-Features loaded.");
	}
}
