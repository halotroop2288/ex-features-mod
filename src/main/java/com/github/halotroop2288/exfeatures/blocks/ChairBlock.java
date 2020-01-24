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
	private static final VoxelShape
	NORTH_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(2, 17, 14, 14, 18, 15), // Top Bar
		Block.createCuboidShape(2, 13.5D, 14, 14, 14.5D, 15), // Bar 1
		Block.createCuboidShape(2, 10, 14, 14, 11, 15), // Bar 2
		Block.createCuboidShape(1, 7, 1, 15, 8, 15), // Seat
		Block.createCuboidShape(14, 0, 14, 15, 18, 15), // Back right leg
		Block.createCuboidShape(1, 0, 14, 2, 18, 15), // Back left leg
		Block.createCuboidShape(13, 0, 1, 15, 7, 3), // Front right leg
		Block.createCuboidShape(1, 0, 1, 3, 7, 3)), // Front left leg
	EAST_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(1, 17, 2, 2, 18, 14), // Top Bar
		Block.createCuboidShape(1, 13.5D, 2, 2, 14.5D, 14), // Bar 1
		Block.createCuboidShape(1, 10, 2, 2, 11, 14), // Bar 2
		Block.createCuboidShape(1, 7, 1, 15, 8, 15), // Seat
		Block.createCuboidShape(1, 0, 14, 2, 18, 15), // Back right leg
		Block.createCuboidShape(1, 0, 1, 2, 18, 2), // Back left leg
		Block.createCuboidShape(13, 0, 13, 15, 7, 15), // Front right leg
		Block.createCuboidShape(13, 0, 1, 15, 7, 3)), // Front left leg
	SOUTH_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(2, 17, 1, 14, 18, 2), // Top Bar
		Block.createCuboidShape(2, 13.5D, 1, 14, 14.5D, 2), // Bar 1
		Block.createCuboidShape(2, 10, 1, 14, 11, 2), // Bar 2
		Block.createCuboidShape(1, 7, 1, 15, 8, 15), // Seat
		Block.createCuboidShape(1, 0, 1, 2, 18, 2), // Back right leg
		Block.createCuboidShape(14, 0, 1, 15, 18, 2), // Back left leg
		Block.createCuboidShape(1, 0, 13, 3, 7, 15), // Front right leg
		Block.createCuboidShape(13, 0, 13, 15, 7, 15)), // Front left leg
	WEST_SHAPE = VoxelShapes.union(
		Block.createCuboidShape(14, 17, 2, 15, 18, 14), // Top Bar
		Block.createCuboidShape(14, 13.5D, 2, 15, 14.5D, 14), // Bar 1
		Block.createCuboidShape(14, 10, 2, 15, 11, 14), // Bar 2
		Block.createCuboidShape(1, 7, 1, 15, 8, 15), // Seat
		Block.createCuboidShape(14, 0, 1, 15, 18, 2), // Back right leg
		Block.createCuboidShape(14, 0, 14, 15, 18, 15), // Back left leg
		Block.createCuboidShape(1, 0, 1, 3, 7, 3), // Front right leg
		Block.createCuboidShape(3, 7, 15, 3, 7, 15)); // Front left leg

	public ChairBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
	}
	
//	@Override
//	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
//	{
//		return SeatEntity.create(world, pos, 0.4, player);
//	}

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
