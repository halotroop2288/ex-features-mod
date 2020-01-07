package com.github.halotroop2288.exfeatures.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CryingObsidianBlock extends Block
{
	public CryingObsidianBlock(Settings settings)
	{ super(settings); }

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack)
	{
		super.onPlaced(world, pos, state, placer, itemStack);
		if (placer instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) placer;
			if (player.getSpawnPosition() != pos.add(0, 2, 0))
				player.setPlayerSpawn(pos.add(0, 2, 0), true, true);
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		if (player.getSpawnPosition() != pos.add(0, 2, 0))
			player.setPlayerSpawn(pos.add(0, 2, 0), true, true);
		return super.onUse(state, world, pos, player, hand, hit);
	}
}
