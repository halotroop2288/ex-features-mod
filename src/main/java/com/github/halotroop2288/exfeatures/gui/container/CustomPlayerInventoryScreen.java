package com.github.halotroop2288.exfeatures.gui.container;

import com.github.halotroop2288.exfeatures.ExFeaturesClient;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.container.PlayerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CustomPlayerInventoryScreen extends AbstractInventoryScreen<PlayerContainer>
{
	public static final Identifier BACKGROUND_TEXTURE = new Identifier("exfeatures", "textures/gui/container/inventory.png");
	private float mouseX;
	private float mouseY;
	public CustomPlayerInventoryScreen(PlayerEntity player)
	{
		super(player.playerContainer, player.inventory, player.getName());
	}
	
	@Override
	protected void init()
	{
		if (this.minecraft.interactionManager.hasCreativeInventory())
			this.minecraft.openScreen(new CreativeInventoryScreen(this.minecraft.player));
		else
		{
			this.x = (this.width - this.containerWidth) / 2;
			this.y = (this.height - this.containerHeight) / 2;
		}
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta)
	{
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		super.render(mouseX, mouseY, delta);
		renderBackground();
	}
	
	@Override
	public void renderBackground()
	{
		super.renderBackground();
		drawBackground(minecraft.getTickDelta(), (int)this.mouseX, (int)this.mouseY);
	}
	
	@Override
	protected void drawForeground(int mouseX, int mouseY)
	{
		this.font.draw(this.title.asFormattedString(), 115F, 4F, 4210752);
		this.font.draw(new TranslatableText("container.crafting").asFormattedString(), 122F, 13.75F, 4210752);
	}

	@Override
	protected void drawBackground(float delta, int mouseX, int mouseY)
	{
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
		int i = this.x;
		int j = this.y;
		this.blit(i, j, 0, 0, this.containerWidth, this.containerHeight);
		ExFeaturesClient.drawPlayerPreview(i + 30, j + 75, 25, minecraft.player);
	}
}
