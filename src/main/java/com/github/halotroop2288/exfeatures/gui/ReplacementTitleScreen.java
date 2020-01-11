package com.github.halotroop2288.exfeatures.gui;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.options.AccessibilityScreen;
import net.minecraft.client.gui.screen.options.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class ReplacementTitleScreen extends TitleScreen
{
	private final boolean doBackgroundFade;
	private float speed = 0.25F, time = 0;
	private String splashText;
	private int copyrightTextWidth, copyrightTextX;
	private boolean isMinceraft;
	private long backgroundFadeStart;
	private static final Identifier EDITION_TITLE_TEXTURE = new Identifier("minecraft", "textures/gui/title/edition.png"),
		ACCESSIBILITY_ICON = new Identifier("minecraft", "textures/gui/accessibility.png");
	private static final String COPYRIGHT_TEXT = "Copyright Mojang AB. Do not distribute!";

	public ReplacementTitleScreen(boolean doBackgroundFade)
	{
		this.doBackgroundFade = doBackgroundFade;
		this.isMinceraft = (double) (new Random()).nextFloat() < 1.0E-4D;
	}

	public ReplacementTitleScreen()
	{ this(false); }

	@Override
	protected void init()
	{
		if (this.splashText == null)
		{ this.splashText = this.minecraft.getSplashTextLoader().get(); }
		this.copyrightTextWidth = this.font.getStringWidth(COPYRIGHT_TEXT);
		this.copyrightTextX = this.width - this.copyrightTextWidth - 2;
		initButtons(this.height / 4 + 48);
	}

	public static CompletableFuture<Void> loadTexturesAsync(TextureManager textureManager, Executor executor)
	{
		return CompletableFuture.allOf(textureManager.loadTextureAsync(EDITION_TITLE_TEXTURE, executor), textureManager.loadTextureAsync(ACCESSIBILITY_ICON, executor),
			textureManager.loadTextureAsync(BACKGROUND_LOCATION, executor), textureManager.loadTextureAsync(GUI_ICONS_LOCATION, executor));
	}

	public boolean isPauseScreen()
	{ return false; }

	public boolean shouldCloseOnEsc()
	{ return false; }

	@Override
	public void render(int mouseX, int mouseY, float delta)
	{
		int j = this.width / 2 - 137;
		float f = this.doBackgroundFade ? (float) (Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0F : 1.0F;
		float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
		int l = MathHelper.ceil(g * 255.0F) << 24;
		renderBackground(mouseX, mouseY, delta);
		time += delta;
		this.minecraft.getTextureManager().bindTexture(EDITION_TITLE_TEXTURE);
		blit(j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
		if (this.splashText != null)
		{
			RenderSystem.pushMatrix();
			RenderSystem.translatef((float) (this.width / 2 + 90), 70.0F, 0.0F);
			RenderSystem.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
			float h = 1.8F - MathHelper.abs(MathHelper.sin((float) (Util.getMeasuringTimeMs() % 1000L) / 1000.0F * 6.2831855F) * 0.1F);
			h = h * 100.0F / (float) (this.font.getStringWidth(this.splashText) + 32);
			RenderSystem.scalef(h, h, h);
			this.drawCenteredString(this.font, this.splashText, 0, -8, 16776960 | l);
			RenderSystem.popMatrix();
		}
	}

	private void initButtons(int offsetFromTop)
	{
		int lastRowOffset = offsetFromTop + 84;
		this.addButton(new ButtonWidget(this.width / 2 - 100, offsetFromTop, 200, 20, I18n.translate("menu.singleplayer"), (buttonWidget) ->
		{
			this.minecraft.openScreen(new SelectWorldScreen(this));
		}));
		this.addButton(new ButtonWidget(this.width / 2 - 100, offsetFromTop + 24 * 1, 200, 20, I18n.translate("menu.multiplayer"), (buttonWidget) ->
		{
			this.minecraft.openScreen(new MultiplayerScreen(this));
		}));
		this.addButton(new ButtonWidget(this.width / 2 - 100, offsetFromTop + 24 * 2, 200, 20, I18n.translate("menu.mods_packs"), (buttonWidget) ->
		{
			this.minecraft.openScreen(new ModsNPacksScreen(this));
		}));
		this.addButton(new ButtonWidget(this.width / 2 - 100, lastRowOffset, 98, 20, I18n.translate("menu.options"), (buttonWidget) ->
		{
			this.minecraft.openScreen(new SettingsScreen(this, this.minecraft.options));
		}));
		this.addButton(new ButtonWidget(this.width / 2 + 2, lastRowOffset, 98, 20, I18n.translate("menu.quit"), (buttonWidget) ->
		{
			this.minecraft.scheduleStop();
		}));
		initAccessibilityButtons(lastRowOffset);
	}

	private void initAccessibilityButtons(int offset)
	{
		this.addButton(
			new TexturedButtonWidget(this.width / 2 - 124, offset, 20, 20, 0, 106, 20, ButtonWidget.WIDGETS_LOCATION, 256, 256, (buttonWidget) ->
			{
				this.minecraft.openScreen(new LanguageOptionsScreen(this, this.minecraft.options, this.minecraft.getLanguageManager()));
			}, I18n.translate("narrator.button.language")));
		this.addButton(new TexturedButtonWidget(this.width / 2 + 104, offset, 20, 20, 0, 0, 20, ACCESSIBILITY_ICON, 32, 64,
			(buttonWidget) ->
			{
				this.minecraft.openScreen(new AccessibilityScreen(this, this.minecraft.options));
			}, I18n.translate("narrator.button.accessibility")));
	}

	private void renderBackground(int mouseX, int mouseY, float tickDelta)
	{
		this.minecraft.getTextureManager().bindTexture(DrawableHelper.BACKGROUND_LOCATION);
		int i = this.width;
		float f = -this.time * 0.5F * this.speed;
		float g = (float) this.height - this.time * 0.5F * this.speed;
		float j = this.time * 0.02F;
		float k = (float) (this.height + this.height + 24) / this.speed;
		float l = (k - 20.0F - this.time) * 0.005F;
		if (l < j)
		{ j = l; }
		if (j > 1.0F)
		{ j = 1.0F; }
		j *= j;
		j = j * 96.0F / 255.0F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		bufferBuilder.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
		bufferBuilder.vertex(0.0D, (double) this.height, (double) this.getBlitOffset()).texture(0.0F, f * 0.015625F).color(j, j, j, 1.0F).next();
		bufferBuilder.vertex((double) i, (double) this.height, (double) this.getBlitOffset()).texture((float) i * 0.015625F, f * 0.015625F).color(j, j, j, 1.0F).next();
		bufferBuilder.vertex((double) i, 0.0D, (double) this.getBlitOffset()).texture((float) i * 0.015625F, g * 0.015625F).color(j, j, j, 1.0F).next();
		bufferBuilder.vertex(0.0D, 0.0D, (double) this.getBlitOffset()).texture(0.0F, g * 0.015625F).color(j, j, j, 1.0F).next();
		tessellator.draw();
	}
}
