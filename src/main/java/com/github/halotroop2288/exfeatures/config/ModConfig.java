package com.github.halotroop2288.exfeatures.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:${configDir}/exfeatures.config"})
public interface ModConfig extends Config
{
	// GUIs
		// Title screen
	@Key("menu.title.use_custom_title_screen")
	@DefaultValue("true")
	public boolean useCustomTitleScreen();
	@Key("menu.title.show_java_edition_logo")
	@DefaultValue("false")
	public boolean showJavaEdition();
	@Key("menu.title.show_unfinisehd_tutorial_button")
	@DefaultValue("false")
	public boolean showUnfinishedTutorialButton();
	@Key("menu.title.show_accessibility_buttons")
	@DefaultValue("false")
	public boolean showAccessibilityButtons();
		// Inventory
	@Key("menu.inventory.use_custom_inventory_screen")
	@DefaultValue("true")
	public boolean useCustomInventoryScreen();
	
	// Registries
	
	@Key("mod.register_blocks")
	@DefaultValue("true")
	public boolean registerBlocks();
	@Key("mod.register_items")
	@DefaultValue("true")
	public boolean registerItems();
	@Key("mod.register_entities")
	@DefaultValue("true")
	public boolean registerEntities();
	@Key("mod.register_paintings")
	@DefaultValue("true")
	public boolean registerPaintings();
	@Key("mod.register_unused_mob_spawns")
	@DefaultValue("true")
	public boolean spawnUnusedMobs();
	
	// Dependencies
	
	@Key("dependencies.use_modmenu_integration")
	@DefaultValue("true")
	public boolean modmenuEnabled();
	@Key("dependencies.use_trinkets_integration")
	@DefaultValue("true")
	public boolean trinketsEnabled();
	@Key("dependencies.use_indev_world_gen_integration")
	@DefaultValue("true")
	public boolean indevEnabled();

	@Key("entities.giant_zombie.spawn.minimum")
	@DefaultValue("0")
	public int giantSpawnMin();
	@Key("entities.giant_zombie.spawn.weight")
	@DefaultValue("1")
	public int giantSpawnMed();
	@Key("entities.giant_zombie.spawn.maximum")
	@DefaultValue("2")
	public int giantSpawnMax();
	@Key("entities.zombie_horse.spawn.minimum")
	@DefaultValue("2")
	public int zombieHorseSpawnMin();
	@Key("entities.zombie_horse.spawn.weight")
	@DefaultValue("2")
	public int zombieHorseSpawnMed();
	@Key("entities.zombie_horse.spawn.maximum")
	@DefaultValue("5")
	public int zombieHorseSpawnMax();
}
