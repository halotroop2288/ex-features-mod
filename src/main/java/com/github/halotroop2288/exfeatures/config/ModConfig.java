package com.github.halotroop2288.exfeatures.config;

import org.aeonbits.owner.Config;

public interface ModConfig extends Config
{
	@Key("title.use.custom")
	@DefaultValue("true")
	public boolean useCustomTitleScreen();
	@Key("title.show.edition")
	@DefaultValue("true")
	public boolean showJavaEdition();
	@Key("title.show.tutorial.button")
	@DefaultValue("false")
	public boolean showTutorialButton();
	@Key("title.show.accessibility.buttons")
	@DefaultValue("false")
	public boolean showAccessibilityButtons();
}
