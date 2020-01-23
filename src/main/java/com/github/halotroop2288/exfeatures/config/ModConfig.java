package com.github.halotroop2288.exfeatures.config;

import org.aeonbits.owner.Config;

public interface ModConfig extends Config
{
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
}
