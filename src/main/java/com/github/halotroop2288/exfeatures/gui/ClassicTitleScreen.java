package com.github.halotroop2288.exfeatures.gui;

import com.github.halotroop2288.exfeatures.ExFeatures;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.datafixers.util.Pair;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.options.AccessibilityScreen;
import net.minecraft.client.gui.screen.options.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.resourcepack.ResourcePackOptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.Matrix4f;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameMode;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelInfo;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.ARBVertexBlend;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.Random;

public class ClassicTitleScreen extends Screen
{
	private static final MatrixStack.Entry MATRIX_STACK_ENTRY = new MatrixStack().peek();
	private static final int MISSING_COLOUR = 0xF000F0;
	private static final float TAU = (float) (Math.PI * 2);
	private static final double BLOCK_SPEED = 1;
	private static String[] MINECRAFT_LOGO =
	{ // @formatter:off
		" *   * * *   * *** *** *** *** *** ***",
		" ** ** * **  * *   *   * * * * *    * ",
		" * * * * * * * **  *   **  *** **   * ",
		" *   * * *  ** *   *   * * * * *    * ",
		" *   * * *   * *** *** * * * * *    * "
    }; // @formatter:on
	private static final ItemStack BRUSH_TEXT = new ItemStack(Blocks.COBBLESTONE);
	private static final char CHAR_TEXT = '*';
	private static Pair<BufferBuilder.DrawArrayParameters, ByteBuffer> textBufferDark;
	private static Pair<BufferBuilder.DrawArrayParameters, ByteBuffer> textBufferLight;
	private static final String MINECRAFT_VERSION = "Minecraft " + SharedConstants.getGameVersion().getName();
	private static final String COPYRIGHT = "Copyright Mojang AB. Do not distribute.";
	private static final Identifier EDITION_TITLE_TEXTURE = new Identifier("minecraft", "textures/gui/title/edition.png");
	private static final Identifier ACCESSIBILITY_ICON = new Identifier("minecraft", "textures/gui/accessibility.png");
	private String splashText;
	private LogoEffectRandomizer[][] logoEffects;

	public ClassicTitleScreen()
	{ super(new TranslatableText("narrator.screen.title")); }

	@Override
	public void init()
	{
		super.init();
		if (this.splashText == null)
		{ this.splashText = this.minecraft.getSplashTextLoader().get(); }
		int buttonWidth = 200;
		int buttonHeight = 20;
		initButtons(this.height / 4 + 48, buttonWidth, buttonHeight);
		if ((double)(new Random()).nextFloat() < 1.0E-4D)
		{
			MINECRAFT_LOGO = new String[]
			{ // @formatter:off
				" *   * * *   * *** *** *** *** *** ***",
				" ** ** * **  * *   *   * * * * *    * ",
				" * * * * * * * *   **  **  *** **   * ",
				" *   * * *  ** *   *   * * * * *    * ",
				" *   * * *   * *** *** * * * * *    * "
		    }; // @formatter:on
		}
		else
		{
			MINECRAFT_LOGO = new String[]
			{ // @formatter:off
				" *   * * *   * *** *** *** *** *** ***",
				" ** ** * **  * *   *   * * * * *    * ",
				" * * * * * * * **  *   **  *** **   * ",
				" *   * * *  ** *   *   * * * * *    * ",
				" *   * * *   * *** *** * * * * *    * "
		    }; // @formatter:on
		}
	}

	@Override
	public void render(int mouseX, int mouseY, float fakePartialTicks)
	{
		float partialTicks = this.minecraft.getTickDelta();
		this.renderDirtBackground(0);
		this.drawLogo(partialTicks);
		if (ExFeatures.config.showJavaEdition())
		{
	        this.minecraft.getTextureManager().bindTexture(EDITION_TITLE_TEXTURE);
	        blit(this.width / 2 - 137 + 88, 90, 0.0F, 0.0F, 98, 14, 128, 16);
		}
		this.drawSplashText();
		this.drawString(this.font, MINECRAFT_VERSION, 2, 2, 0xFF505050);
		this.drawString(this.font, COPYRIGHT, this.width - this.font.getStringWidth(COPYRIGHT) - 2, this.height - 10, 0xFFFFFFFF);
		super.render(mouseX, mouseY, partialTicks);
	}

	private void initButtons(int offsetFromTop, int buttonWidth, int buttonHeight)
	{
		int row2Offset = offsetFromTop + 24;
		int row3Offset = offsetFromTop + 24 * 2;
		int row4Offset = offsetFromTop + 24 * 3;
		int lastRowOffset = offsetFromTop + 24 * 4;
		int xOffset = this.width / 2 - 100;
		this.addButton(new ButtonWidget(xOffset, offsetFromTop, 200, 20, I18n.translate("menu.singleplayer"), (buttonWidget) ->
		{
			this.minecraft.openScreen(new SelectWorldScreen(this));
		}));
		this.addButton(new ButtonWidget(xOffset, row2Offset, 200, 20, I18n.translate("menu.multiplayer"), (buttonWidget) ->
		{
			this.minecraft.openScreen(new MultiplayerScreen(this));
		}));
		if (FabricLoader.getInstance().isModLoaded("modmenu"))
			this.addButton(new ButtonWidget(xOffset, row3Offset, 200, 20, I18n.translate("menu.modspacks"), (buttonWidget) ->
			{
				this.minecraft.openScreen(new ModsNPacksScreen(this));
			}));
		else
			this.addButton(new ButtonWidget(xOffset, row3Offset, 200, 20, I18n.translate("options.resourcepack"), (buttonWidget) ->
			{
				this.minecraft.openScreen(new ResourcePackOptionsScreen(this, this.minecraft.options));
			}));
		if (ExFeatures.config.showUnfinishedTutorialButton())
			this.addButton(new ButtonWidget(xOffset, row4Offset, 200, 20, I18n.translate("menu.playtutorial"), (buttonWidget) ->
				{
					this.minecraft.startIntegratedServer("Tutorial", "Tutorial", new LevelInfo(0, GameMode.SURVIVAL, true, true, LevelGeneratorType.DEFAULT));
				}));
		this.addButton(new ButtonWidget(xOffset, lastRowOffset, 98, 20, I18n.translate("menu.options"), (buttonWidget) ->
		{
			this.minecraft.openScreen(new SettingsScreen(this, this.minecraft.options));
		}));
		this.addButton(new ButtonWidget(this.width / 2 + 2, lastRowOffset, 98, 20, I18n.translate("menu.quit"), (buttonWidget) ->
		{
			this.minecraft.scheduleStop();
		}));
		if (ExFeatures.config.showAccessibilityButtons())
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

	@Override
	public void tick()
	{
		super.tick();
		if (this.logoEffects != null)
		{
			for (LogoEffectRandomizer[] logoEffect : this.logoEffects)
			{
				for (LogoEffectRandomizer aLogoEffect : logoEffect)
				{ aLogoEffect.update(); }
			}
		}
	}

	private void drawSplashText()
	{
		double scale = 2 - Math.abs(MathHelper.sin(((System.currentTimeMillis() % 1000L) / 1000.0F) * TAU) * 0.1);
		int splashWidth = this.font.getStringWidth(this.splashText);
		scale *= 100 / (float) (splashWidth + 32);
		GL11.glPushMatrix();
		GL11.glTranslated(0.5 * this.width + 115, 78, 0);
		GL11.glRotated(-20, 0, 0, 1);
		GL11.glScaled(scale, scale, scale);
		this.font.drawWithShadow(this.splashText, -splashWidth * 0.5F, -8, 0xFFFFFF00);
		GL11.glPopMatrix();
	}

	private static Pair<BufferBuilder.DrawArrayParameters, ByteBuffer> bakeBlock(ItemStack itemStack, float brightness)
	{
		BakedModel model = MinecraftClient.getInstance().getItemRenderer().getModels().getModel(itemStack);
		BufferBuilder buffer = new BufferBuilder(4 * Direction.values().length);
		buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL);
		Random random = new Random();
		for (Direction direction : Direction.values())
		{
			for (BakedQuad quad : model.getQuads(null, direction, random))
			{ buffer.quad(MATRIX_STACK_ENTRY, quad, brightness, brightness, brightness, MISSING_COLOUR, OverlayTexture.DEFAULT_UV); }
		}
		buffer.end();
		return buffer.popData();
	}

	private static final class LogoEffectRandomizer
	{
		public double position;
		public double lastTickPosition;
		public double velocity;

		private LogoEffectRandomizer(final Random rand, final int xIndex, final int yIndex)
		{
			this.position = this.lastTickPosition = (10 + yIndex) + rand.nextDouble() * 32.0D + xIndex; // Alpha
			// this.position = this.lastTickPosition = 40 + (this.rand.nextDouble() * 50D); // Normal
			// this.position = this.lastTickPosition = (double) (10 + yIndex) + this.rand.nextDouble() * 32D + (double) xIndex; // Left to right
			// this.position = this.lastTickPosition = (double) 120 + (double) -Math.abs(xIndex - 24) * 4; // Outside to middle
			// this.position = this.lastTickPosition = (double) 120 + (double) -Math.abs(xIndex - 24) * 4 + Math.abs(yIndex - 4.5) * 10; // ?
		}

		public void update()
		{
			this.lastTickPosition = this.position;
			if (this.position <= 0)
			{ return; }
			if (this.position > 0.0D)
			{ this.velocity -= 0.6 * ClassicTitleScreen.BLOCK_SPEED; }
			this.position += this.velocity;
			this.velocity *= 0.9;
			if (this.position <= 0.0D)
			{
				this.position = 0.0D;
				this.velocity = 0.0D;
			}
		}
	}

	private void drawLogo(float partialTicks)
	{
		// TODO: move to a better spot
		if (textBufferDark == null)
		{ textBufferDark = bakeBlock(BRUSH_TEXT, 0); }
		if (textBufferLight == null)
		{ textBufferLight = bakeBlock(BRUSH_TEXT, 1); }
		if (this.logoEffects == null)
		{
			this.logoEffects = new LogoEffectRandomizer[ClassicTitleScreen.MINECRAFT_LOGO[0].length()][ClassicTitleScreen.MINECRAFT_LOGO.length];
			Random random = new Random();
			for (int i = 0; i < this.logoEffects.length; i++)
			{
				for (int j = 0; j < this.logoEffects[i].length; j++)
				{ this.logoEffects[i][j] = new LogoEffectRandomizer(random, i, j); }
			}
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		Window window = this.minecraft.getWindow();
		int width = window.getWidth();
		int height = window.getHeight();
		double scaleFactor = window.getScaleFactor();
		int k = (int) (170 * scaleFactor);
		GlStateManager.multMatrix(Matrix4f.viewboxMatrix(70.0F, (float) width / k, 0.05F, 100.0F));
		GL11.glViewport(0, height - k, width, k);
		GL11.glMatrixMode(ARBVertexBlend.GL_MODELVIEW0_ARB);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glDepthMask(true);
		this.minecraft.getTextureManager().bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
		this.minecraft.getTextureManager().getTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX).setFilter(false, false);
		GlStateManager.enableDepthTest();
		for (int pass = 0; pass < 2; pass++)
		{
			GL11.glPushMatrix();
			GL11.glTranslated(0, 2, -15);
			GL11.glScalef(1.0F, -1.0F, 1.0F);
			GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(0.89F, 1.0F, 0.4F);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glTranslatef(-ClassicTitleScreen.MINECRAFT_LOGO[0].length() * 0.5F, -ClassicTitleScreen.MINECRAFT_LOGO.length * 0.5F, 0.0F);
			for (int y = 0; y < ClassicTitleScreen.MINECRAFT_LOGO.length; y++)
			{
				for (int x = 0; x < ClassicTitleScreen.MINECRAFT_LOGO[y].length(); x++)
				{
					char c = ClassicTitleScreen.MINECRAFT_LOGO[y].charAt(x);
					if (c == ' ')
						continue;
					LogoEffectRandomizer logoeffectrandomizer = this.logoEffects[x][y];
					float position = (float) (logoeffectrandomizer.lastTickPosition
						+ ((logoeffectrandomizer.position - logoeffectrandomizer.lastTickPosition) * (double) partialTicks));
					float scale = 1.0F;
					if (pass == 0 && position == 0)
					{ continue; }
					GL11.glPushMatrix();
					if (pass == 0)
					{
						scale = (position * 0.04F) + 1.0F;
						position *= 0.05;
					}
					GL11.glTranslatef(x, y, position);
					GL11.glScalef(scale, scale, scale);
					if (c == CHAR_TEXT)
					{ draw(pass == 0 ? textBufferDark : textBufferLight); }
					GL11.glPopMatrix();
				}
			}
			GL11.glPopMatrix();
		}
		this.minecraft.getTextureManager().bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(ARBVertexBlend.GL_MODELVIEW0_ARB);
		GL11.glPopMatrix();
		GL11.glViewport(0, 0, width, height);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	private static void draw(Pair<BufferBuilder.DrawArrayParameters, ByteBuffer> data)
	{
		BufferBuilder.DrawArrayParameters params = data.getFirst();
		ByteBuffer byteBuffer = data.getSecond();
		byteBuffer.clear();
		params.getVertexFormat().startDrawing(MemoryUtil.memAddress(byteBuffer));
		GlStateManager.drawArrays(params.getMode(), 0, params.getCount());
		params.getVertexFormat().endDrawing();
	}

	@Override
	public boolean keyPressed(int key, int scancode, int action)
	{
		if (key == GLFW.GLFW_KEY_ESCAPE)
		{
			this.minecraft.openScreen(new ClassicTitleScreen());
			return true;
		}
		return super.keyPressed(key, scancode, action);
	}
}
