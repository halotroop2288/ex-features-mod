package com.github.halotroop2288.exfeatures.blocks;

import com.github.halotroop2288.exfeatures.gui.container.LockedChestScreen;
import com.ibm.icu.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LockedChestBlock extends Block
{
	MinecraftClient client = MinecraftClient.getInstance();
    Calendar calendar = Calendar.getInstance();
	protected LockedChestBlock(Settings settings)
	{ super(settings); }
	
	
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		if (calendar.get(2) + 1 == 4 && calendar.get(5) == 1)
		{
			client.openScreen(new LockedChestScreen());
			return ActionResult.SUCCESS;
		}
		else return ActionResult.PASS;
	}
	
	@Override
	public int getLuminance(BlockState state)
	{ return 15; }
}
