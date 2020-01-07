package com.github.halotroop2288.exfeatures.mixin;

import java.nio.ByteBuffer;
import java.util.Random;

import org.lwjgl.opengl.ARBVertexBlend;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.halotroop2288.exfeatures.gui.LogoEffectRandomizer;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.datafixers.util.Pair;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
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
import net.minecraft.text.Text;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameMode;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen
{
	// @formatter:off
	private static String[] MINECRAFT_LOGO =
	{
		" *   * * *   * *** *** *** *** *** ***",
		" ** ** * **  * *   *   * * * * *    * ",
		" * * * * * * * **  *   **  *** **   * ",
		" *   * * *  ** *   *   * * * * *    * ",
		" *   * * *   * *** *** * * * * *    * "
	};// @formatter:on
	
	private static final ItemStack BRUSH_TEXT = new ItemStack(Blocks.STONE);
	private static final char CHAR_TEXT = '*';
	private LogoEffectRandomizer[][] logoEffects;
	private static final MatrixStack.Entry MATRIX_STACK_ENTRY = new MatrixStack().peek();
	private static final int MISSING_COLOUR = 0xF000F0;

    private static Pair<BufferBuilder.DrawArrayParameters, ByteBuffer> textBufferDark;
    private static Pair<BufferBuilder.DrawArrayParameters, ByteBuffer> textBufferLight;

	public TitleScreenMixin(Text title)
	{
		super(title);
		System.out.println("Starting TitleScreenMixin...");
	}

	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info)
	{ System.out.println("Successfully injected the Title Screen."); }

	@Inject(at = @At("TAIL"), method = "tick()V")
	public void tick(CallbackInfo info)
	{
		if (this.logoEffects != null)
		{
			for (LogoEffectRandomizer[] logoEffect : this.logoEffects)
			{
				for (LogoEffectRandomizer aLogoEffect : logoEffect)
				{ aLogoEffect.update(); }
			}
		}
	}

	@Inject(at = @At("RETURN"), method = "initWidgetsNormal(II)V")
	public void drawMenuButton(CallbackInfo info)
	{
		this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 48 + 24 * 3, 200, 20, I18n.translate("menu.playtutorial"), buttonwidget ->
		{
			this.minecraft.startIntegratedServer("Tutorial_World", "Tutorial_World",
				new LevelInfo(0, GameMode.SURVIVAL, true, true, LevelGeneratorType.DEFAULT));
		}));
		System.out.println("Placed tutorial button");
	}

	@Inject(at = @At("HEAD"), method = "render", remap = false)
	private void render(CallbackInfo info)
	{
		float partialTicks = this.minecraft.getTickDelta();
		this.drawLogo(partialTicks);
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

	public void drawLogo(float partialTicks)
	{
		if (this.logoEffects == null)
		{
			System.out.println("Creating Logo Effects");
			this.logoEffects = new LogoEffectRandomizer[MINECRAFT_LOGO[0].length()][MINECRAFT_LOGO.length];
			Random random = new Random();
			for (int i = 0; i < this.logoEffects.length; i++)
			{
				for (int j = 0; j < this.logoEffects[i].length; j++)
				{ this.logoEffects[i][j] = new LogoEffectRandomizer(random, i, j); }
			}
			System.out.println("Defining textBuffers");
			textBufferLight = bakeBlock(BRUSH_TEXT, 1);
			textBufferDark = bakeBlock(BRUSH_TEXT, 0);
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
			GL11.glTranslatef(-MINECRAFT_LOGO[0].length() * 0.5F, -MINECRAFT_LOGO.length * 0.5F, 0.0F);
			for (int y = 0; y < MINECRAFT_LOGO.length; y++)
			{
				for (int x = 0; x < MINECRAFT_LOGO[y].length(); x++)
				{
					char c = MINECRAFT_LOGO[y].charAt(x);
					if (c == ' ')
					{
						continue;
					}
					
					LogoEffectRandomizer logoeffectrandomizer = this.logoEffects[x][y];
					float position = (float) (logoeffectrandomizer.lastTickPosition +
						((logoeffectrandomizer.position - logoeffectrandomizer.lastTickPosition) * (double) partialTicks));
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
					{
						if (pass == 0)
							draw(textBufferDark);
						else
							draw(textBufferLight);
					}
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
}
