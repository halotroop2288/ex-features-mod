package com.github.halotroop2288.exfeatures;

import org.aeonbits.owner.ConfigFactory;

import com.github.halotroop2288.exfeatures.config.ModConfig;
import com.github.halotroop2288.exfeatures.registries.*;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class ExFeatures implements ModInitializer
{
	public static ModConfig config;
	public static boolean useModMenu;
	public static boolean useTrinkets;
	public static boolean useIndev;
	
	@Override
	public void onInitialize()
	{
	    ConfigFactory.setProperty("configDir", FabricLoader.getInstance().getConfigDirectory().toString() + "/ex-features.cfg");
	    
	    config = ConfigFactory.create(ModConfig.class);
	    useModMenu = FabricLoader.getInstance().isModLoaded("modmenu") && config.modmenuEnabled();
	    useTrinkets = FabricLoader.getInstance().isModLoaded("trinkets") && config.trinketsEnabled();
	    useIndev = FabricLoader.getInstance().isModLoaded("indevworldgen") && config.indevEnabled();
	    
		SoundRegistry.registerSounds(); // Sounds must be registered for other features
		if (config.registerEntities()) EntityRegistry.registerEntities();
		if (config.spawnUnusedMobs()) EntityRegistry.registerEntitySpawns();
		if (config.registerBlocks()) BlockRegistry.registerBlocks();
		if (config.registerItems()) ItemRegistry.registerItems();
		if (config.registerPaintings()) PaintingRegistry.registerPaintings();
		System.out.println("Ex-Features loaded.");
	}
}
