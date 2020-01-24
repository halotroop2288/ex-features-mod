package com.github.halotroop2288.exfeatures.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

// How do I even add sources?
@SuppressWarnings("unused")
public interface ModConfig extends Config
{
	// GUIs
		// Title screen
	@Key("title.use_custom_title_screen")
	@DefaultValue("true")
	public boolean useCustomTitleScreen();
	@Key("title.show_java_edition_logo")
	@DefaultValue("true")
	public boolean showJavaEdition();
	@Key("title.show_unfinisehd_tutorial_button")
	@DefaultValue("false")
	public boolean showUnfinishedTutorialButton();
	@Key("title.show_accessibility_buttons")
	@DefaultValue("false")
	public boolean showAccessibilityButtons();
	
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
}
