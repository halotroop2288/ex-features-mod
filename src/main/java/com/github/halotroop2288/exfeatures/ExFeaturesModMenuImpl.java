package com.github.halotroop2288.exfeatures;

import java.util.function.Function;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class ExFeaturesModMenuImpl implements ModMenuApi
{
	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory()
	{
		return (screen) ->
		{
			screen = new ConfigScreen(); // TODO: Make a config screen
			return screen;
		};
	}

	private class ConfigScreen extends CottonClientScreen
	{
		protected ConfigScreen()
		{ super(new TranslatableText("menu.exfeatures.configuration"), new ConfigGui()); }

		@Override
		protected void init()
		{ super.init(); }
	}

	private class ConfigGui extends LightweightGuiDescription
	{
		public ConfigGui()
		{
			WGridPanel root = new WGridPanel();
			setRootPanel(root);
			root.setSize(200, 200);
			WSprite icon = new WSprite(new Identifier("exfeatures:icon.png"));
			root.add(icon, 0, 1, 1, 1);
			WButton button = new WButton(new TranslatableText("menu.exfeatures.toggle_titlescreen_edition_logo"));
			button.isEnabled(); // TODO: Finish this config menu
			root.add(button, 0, 3, 4, 1);
			root.validate(this);
		}
	}

	@Override
	public String getModId()
	{ return "exfeatures"; }
}
