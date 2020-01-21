package com.github.halotroop2288.exfeatures.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TableBlock extends Block implements Waterloggable
{
	private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	private static final VoxelShape TABLE_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(2.0D, 0.0D, 12.0D, 4.0D, 9.0D, 14.0D),
		Block.createCuboidShape(12.0D, 0.0D, 2.0D, 14.0D, 9.0D, 4.0D), Block.createCuboidShape(12.0D, 0.0D, 12.0D, 14.0D, 9.0D, 14.0D),
		Block.createCuboidShape(2.0D, 0.0D, 2.0D, 4.0D, 9.0D, 4.0D), Block.createCuboidShape(2.0D, 9.0D, 2.0D, 14.0D, 11.0D, 14.0D));

	public TableBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(super.getDefaultState().with(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos)
	{ return TABLE_SHAPE; }

	@Override
	public FluidState getFluidState(BlockState state)
	{ return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state); }

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx)
	{
		BlockPos blockpos = ctx.getBlockPos();
		BlockState blockstate = ctx.getWorld().getBlockState(blockpos);
		if (blockstate.getBlock() == this)
			return blockstate.with(WATERLOGGED, Boolean.valueOf(false));
		else
		{
			FluidState ifluidstate = ctx.getWorld().getFluidState(blockpos);
			return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
		}
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder)
	{ builder.add(WATERLOGGED); }
}
