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
		EntityRegistry.registerEntities(config.registerEntities());
		EntityRegistry.registerEntitySpawns(config.spawnUnusedMobs());
		BlockRegistry.registerBlocks(config.registerBlocks());
		ItemRegistry.registerItems(config.registerItems());
		PaintingRegistry.registerPaintings(config.registerPaintings());
		System.out.println("Ex-Features loaded.");
	}
}
