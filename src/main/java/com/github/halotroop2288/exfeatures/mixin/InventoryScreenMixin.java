package com.github.halotroop2288.exfeatures.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.container.PlayerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends AbstractInventoryScreen<PlayerContainer>
{
	public InventoryScreenMixin(PlayerContainer container, PlayerInventory playerInventory, Text text)
	{ super(container, playerInventory, text); }

	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info)
	{ System.out.println("Successfully injected the Inventory Screen."); }

	@Inject(at = @At("HEAD"), method = "render")
	private void render(CallbackInfo info)
	{
		// TODO: Add XP counter, level counter and player name to screen
	}
}
