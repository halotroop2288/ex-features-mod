package com.github.halotroop2288.exfeatures.gui.container;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ContainerProvider;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class AbstractCustomInventoryScreen<T extends Container> extends Screen implements ContainerProvider<T>
{
	protected int containerWidth = 176;
	protected int containerHeight = 166;
	protected int x, y;
	protected final T container;
	
	public AbstractCustomInventoryScreen(T container, PlayerEntity player, Text title)
	{
		super(title);
		this.container = container;
	}
	
	@Override
	protected void init()
	{
		super.init();
		this.x = (this.width - this.containerWidth) / 2;
		this.y = (this.height - this.containerHeight) / 2;
	}
	
	@Override
	public T getContainer()
	{ return container; }
}
