package com.github.halotroop2288.exfeatures.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

public class ModsNPacksScreen extends Screen
{
	private Screen parent;
	
	public ModsNPacksScreen(Screen parent)
	{
		super(new TranslatableText("menu.mods_packs"));
		this.parent = parent;
	}
	
	@Override
	public void onClose()
	{ minecraft.openScreen(parent); }
}
