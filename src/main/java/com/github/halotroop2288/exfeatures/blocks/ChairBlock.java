package com.github.halotroop2288.exfeatures.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.EntityContext;
import net.minecraft.fluid.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.BlockView;

public class ChairBlock extends Block implements Waterloggable
{
	private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	public static final DirectionProperty FACING = Properties.FACING;
	private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(14.0D, 8.0D, 12.0D, 2.0D, 16.0D, 14.0D), Block.createCuboidShape(2.0D, 0.0D, 12.0D, 4.0D, 6.0D, 14.0D),
		Block.createCuboidShape(12.0D, 0.0D, 2.0D, 14.0D, 6.0D, 4.0D), Block.createCuboidShape(12.0D, 0.0D, 12.0D, 14.0D, 6.0D, 14.0D),
		Block.createCuboidShape(2.0D, 0.0D, 2.0D, 4.0D, 6.0D, 4.0D), Block.createCuboidShape(2.0D, 6.0D, 2.0D, 14.0D, 8.0D, 14.0D));
	private static final VoxelShape EAST_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(2.0D, 8.0D, 14.0D, 4.0D, 16.0D, 2.0D), Block.createCuboidShape(2.0D, 0.0D, 12.0D, 4.0D, 6.0D, 14.0D),
		Block.createCuboidShape(12.0D, 0.0D, 2.0D, 14.0D, 6.0D, 4.0D), Block.createCuboidShape(12.0D, 0.0D, 12.0D, 14.0D, 6.0D, 14.0D),
		Block.createCuboidShape(2.0D, 0.0D, 2.0D, 4.0D, 6.0D, 4.0D), Block.createCuboidShape(2.0D, 6.0D, 2.0D, 14.0D, 8.0D, 14.0D));
	private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(14.0D, 8.0D, 2.0D, 2.0D, 16.0D, 4.0D), Block.createCuboidShape(2.0D, 0.0D, 12.0D, 4.0D, 6.0D, 14.0D),
		Block.createCuboidShape(12.0D, 0.0D, 2.0D, 14.0D, 6.0D, 4.0D), Block.createCuboidShape(12.0D, 0.0D, 12.0D, 14.0D, 6.0D, 14.0D),
		Block.createCuboidShape(2.0D, 0.0D, 2.0D, 4.0D, 6.0D, 4.0D), Block.createCuboidShape(2.0D, 6.0D, 2.0D, 14.0D, 8.0D, 14.0D));
	private static final VoxelShape WEST_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(14.0D, 8.0D, 14.0D, 12.0D, 16.0D, 2.0D), Block.createCuboidShape(2.0D, 0.0D, 12.0D, 4.0D, 6.0D, 14.0D),
		Block.createCuboidShape(12.0D, 0.0D, 2.0D, 14.0D, 6.0D, 4.0D), Block.createCuboidShape(12.0D, 0.0D, 12.0D, 14.0D, 6.0D, 14.0D),
		Block.createCuboidShape(2.0D, 0.0D, 2.0D, 4.0D, 6.0D, 4.0D), Block.createCuboidShape(2.0D, 6.0D, 2.0D, 14.0D, 8.0D, 14.0D));

	public ChairBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos)
	{
		switch ((Direction) state.get(FACING))
		{
		case NORTH:
		default:
			return NORTH_SHAPE;
		case SOUTH:
			return SOUTH_SHAPE;
		case EAST:
			return EAST_SHAPE;
		case WEST:
			return WEST_SHAPE;
		}
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{ builder.add(FACING, WATERLOGGED); }

	@Override
	public boolean isSimpleFullBlock(BlockState state, BlockView view, BlockPos pos)
	{ return false; }

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{ return BlockRenderType.MODEL; }

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
			return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER)).with(FACING, ctx.getPlayerFacing().getOpposite());
		}
	}
}
