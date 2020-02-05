package com.github.halotroop2288.exfeatures.entities;

import java.util.List;

import com.github.halotroop2288.exfeatures.registries.EntityRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnGlobalS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

/** Authors: MrCrayfish, halotroop2288
 * Source: MrCrayfish's Furniture Mod
 * Modified for Fabric */
public class SeatEntity extends Entity
{
	private BlockPos source;
	
	public SeatEntity(EntityType<? extends SeatEntity> entityType, World world)
	{
		super(entityType, world);
		this.noClip = true;
	}
	
	private SeatEntity(World world, BlockPos source, double yOffset)
	{
		this(EntityRegistry.SEAT, world);
		this.source = source;
		this.setPos(source.getX() + 0.5, source.getY() + yOffset, source.getZ() + 0.5);
	}
	
	@Override
	public void tick()
	{
		super.tick();
		if (source == null) source = new BlockPos(this.getPos());
		if (!this.world.isClient)
		{
			if (this.getPassengerList().isEmpty() || this.world.isAir(source))
			{
				this.remove();
				world.updateNeighbor(new BlockPos(this.getPos()), world.getBlockState(new BlockPos(this.getPos())).getBlock(), new BlockPos(this.getPos()));
			}
		}
	}
	
	@Override
	protected void readCustomDataFromTag(CompoundTag tag)
	{}
	
	@Override
	protected void writeCustomDataToTag(CompoundTag tag)
	{}
	
	@Override
	public double getMountedHeightOffset()
	{ return 0; }
	
	public BlockPos getSource()
	{ return source; }
	
	@Override
	public boolean canBeRiddenInWater()
	{ return true; }
	
	@Override
	protected void initDataTracker()
	{}
	
	@Override
	public Packet<?> createSpawnPacket()
	{ return new EntitySpawnGlobalS2CPacket(this); }
	
	public static ActionResult create(World world, BlockPos pos, double yOffset, PlayerEntity player)
	{
		List<SeatEntity> seats = world.getEntitiesIncludingUngeneratedChunks(
			SeatEntity.class,
			new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0)
		);
		if (seats.isEmpty())
		{
			SeatEntity seat = new SeatEntity(world, pos, yOffset);
			world.spawnEntity(seat);
			player.startRiding(seat, true);
			return ActionResult.SUCCESS;
		}
		else return ActionResult.FAIL;
	}
}