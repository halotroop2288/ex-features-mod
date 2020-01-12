package com.github.halotroop2288.exfeatures.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class LockedChestScreen extends Screen
{
	MinecraftClient client = MinecraftClient.getInstance();
	private final int width = 120, height = 120;
	private static final Identifier TEXTURE = new Identifier("exfeatures", "textures/gui/container/locked_chest.png");

	public LockedChestScreen()
	{ super(new TranslatableText("narator.screen.locked_chest")); }

	@Override
	protected void init() // TODO: position the buttons correctly
	{
		String link = "https://www.minecraftshop.com/"; // TODO: Recreate the original site and link to that via some free hosting site
		addButton(new ButtonWidget(0, 0, 0, 0, "menu.locked_chest.stop", (button) ->
		{
			client.openScreen(null);
		}));
		addButton(new ButtonWidget(0, 0, 0, 0, "menu.locked_chest.shop", (button) ->
		{
			System.out.println("April fools!");
			this.minecraft.openScreen(new ConfirmChatLinkScreen((bl) ->
			{
				if (bl) Util.getOperatingSystem().open(link);
				this.minecraft.openScreen(this);
			}, link, true));
		}));
	}

	public static Identifier getTexture()
	{ return TEXTURE; }
}
