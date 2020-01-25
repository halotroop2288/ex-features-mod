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
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TableBlock extends Block implements Waterloggable
{
	private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	private static final DirectionProperty FACING = Properties.FACING;
	private static final VoxelShape
	SHAPE_UP = VoxelShapes.union(
		Block.createCuboidShape(2, 0, 2, 4, 15, 4),
		Block.createCuboidShape(12, 0, 2, 14, 15, 4),
		Block.createCuboidShape(12, 0, 12, 14, 15, 14),
		Block.createCuboidShape(2, 0, 12, 4, 15, 14),
		Block.createCuboidShape(1, 15, 1, 15, 16, 15)
	),
	SHAPE_DOWN = VoxelShapes.union(
		Block.createCuboidShape(1, 0, 1, 15, 1, 15),
		Block.createCuboidShape(12, 0, 2, 14, 15, 4),
		Block.createCuboidShape(12, 0, 12, 14, 15, 14),
		Block.createCuboidShape(2, 0, 12, 4, 15, 14),
		Block.createCuboidShape(1, 15, 1, 15, 16, 15)
	);
	
	public TableBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(super.getDefaultState().with(FACING, Direction.UP).with(WATERLOGGED, false));
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos)
	{
		switch (state.get(FACING))
		{
		default:
		case UP:
			return SHAPE_UP;
		case DOWN:
			return SHAPE_DOWN;
		}
	}
	
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
			return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER)).with(FACING, ctx.getSide());
		}
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder)
	{ builder.add(FACING, WATERLOGGED); }
}
