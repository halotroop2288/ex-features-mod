package com.github.halotroop2288.exfeatures.gui;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.screen.resourcepack.AvailableResourcePackListWidget;
import net.minecraft.client.gui.screen.resourcepack.ResourcePackListWidget;
import net.minecraft.client.gui.screen.resourcepack.ResourcePackOptionsScreen;
import net.minecraft.client.gui.screen.resourcepack.SelectedResourcePackListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.resource.ClientResourcePackProfile;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public class ModsNPacksScreen extends GameOptionsScreen
{
	private Screen parent;
	private AvailableResourcePackListWidget availablePacks;
	private SelectedResourcePackListWidget enabledPacks;
	private boolean dirty;

	public ModsNPacksScreen(Screen parent, GameOptions gameOptions)
	{
		super(parent, gameOptions, new TranslatableText("menu.mods_packs"));
		this.parent = parent;
	}

	@Override
	public void onClose()
	{ minecraft.openScreen(parent); }

	@Override
	public void init(MinecraftClient client, int width, int height)
	{
		this.addButton(new ButtonWidget(this.width / 2 - 154, this.height - 48, 150, 20, I18n.translate("resourcePack.openFolder"), (buttonWidget) ->
		{
			Util.getOperatingSystem().open(this.minecraft.getResourcePackDir());
		}));
		this.addButton(new ButtonWidget(this.width / 2 + 4, this.height - 48, 150, 20, I18n.translate("gui.done"), (buttonWidget) ->
		{
			if (this.dirty)
			{
				List<ClientResourcePackProfile> list = Lists.newArrayList();
				@SuppressWarnings("rawtypes") Iterator var3 = this.enabledPacks.children().iterator();
				while (var3.hasNext())
				{
					ResourcePackListWidget.ResourcePackEntry resourcePackEntry = (ResourcePackListWidget.ResourcePackEntry) var3.next();
					list.add(resourcePackEntry.getPack());
				}
				Collections.reverse(list);
				this.minecraft.getResourcePackManager().setEnabledProfiles(list);
				this.gameOptions.resourcePacks.clear();
				this.gameOptions.incompatibleResourcePacks.clear();
				var3 = list.iterator();
				while (var3.hasNext())
				{
					ClientResourcePackProfile clientResourcePackProfile = (ClientResourcePackProfile) var3.next();
					if (!clientResourcePackProfile.isPinned())
					{
						this.gameOptions.resourcePacks.add(clientResourcePackProfile.getName());
						if (!clientResourcePackProfile.getCompatibility().isCompatible())
						{ this.gameOptions.incompatibleResourcePacks.add(clientResourcePackProfile.getName()); }
					}
				}
				this.gameOptions.write();
				this.minecraft.openScreen(this.parent);
				this.minecraft.reloadResources();
			}
			else
			{
				this.minecraft.openScreen(this.parent);
			}
		}));
		SelectedResourcePackListWidget selectedResourcePackListWidget = this.enabledPacks;
		this.availablePacks = new AvailableResourcePackListWidget(this.minecraft, 200, this.height);
		this.availablePacks.setLeftPos(this.width / 2 - 4 - 200);
		this.children.add(this.availablePacks);
		this.enabledPacks = new SelectedResourcePackListWidget(this.minecraft, 200, this.height);
		this.enabledPacks.setLeftPos(this.width / 2 + 4);
		if (selectedResourcePackListWidget != null)
		{
			selectedResourcePackListWidget.children().forEach((resourcePackEntry) ->
			{
				this.enabledPacks.children().add(resourcePackEntry);
				resourcePackEntry.method_24232(this.enabledPacks);
			});
		}
		this.children.add(this.enabledPacks);
		if (!this.dirty)
		{
			this.availablePacks.children().clear();
			this.enabledPacks.children().clear();
			ResourcePackManager<ClientResourcePackProfile> resourcePackManager = this.minecraft.getResourcePackManager();
			resourcePackManager.scanPacks();
			List<ClientResourcePackProfile> list = Lists.newArrayList(resourcePackManager.getProfiles());
			list.removeAll(resourcePackManager.getEnabledProfiles());
			Iterator<?> var5 = list.iterator();
			ClientResourcePackProfile clientResourcePackProfile2;
			while (var5.hasNext())
			{
				clientResourcePackProfile2 = (ClientResourcePackProfile) var5.next();
				this.availablePacks
					.add(new ResourcePackListWidget.ResourcePackEntry(this.availablePacks, ((ResourcePackOptionsScreen)(Object)this), clientResourcePackProfile2));
			}
			var5 = Lists.reverse(Lists.newArrayList(resourcePackManager.getEnabledProfiles())).iterator();
			while (var5.hasNext())
			{
				clientResourcePackProfile2 = (ClientResourcePackProfile) var5.next();
				this.enabledPacks
					.add(new ResourcePackListWidget.ResourcePackEntry(this.enabledPacks, ((ResourcePackOptionsScreen)(Object)this), clientResourcePackProfile2));
			}
		}
	}
}
