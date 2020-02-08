package com.github.halotroop2288.exfeatures.gui.menu;

import io.github.prospector.modmenu.gui.ModListScreen;
import io.github.prospector.modmenu.gui.ModMenuButtonWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.resourcepack.ResourcePackOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TranslatableText;

public class ModsNPacksScreen extends Screen
{
	Screen parent;
	MinecraftClient client = MinecraftClient.getInstance();

	public ModsNPacksScreen(Screen parent)
	{
		super(new TranslatableText("menu.mods_packs.title"));
		this.parent = parent;
	}

	@Override
	protected void init()
	{
		int offsetFromTop = (this.height / 4) + 60;
		this.addButton(new ModMenuButtonWidget(this.width / 2 - 150, offsetFromTop, 150, 20, I18n.translate("modmenu.title"), parent));
		this.addButton(new ButtonWidget(this.width / 2, offsetFromTop, 150, 20, I18n.translate("options.resourcepack"), (buttonWidget) ->
		{
			client.openScreen(new ResourcePackOptionsScreen(parent, this.client.options));
		}));
	}

	@Override
	public void render(int mouseX, int mouseY, float delta)
	{
		this.renderDirtBackground(0);
		super.render(mouseX, mouseY, delta);
	}

	@Override
	public void onClose()
	{ minecraft.openScreen(parent); }
}
