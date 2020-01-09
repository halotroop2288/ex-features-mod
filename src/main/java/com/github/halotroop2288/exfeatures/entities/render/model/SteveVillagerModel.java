package com.github.halotroop2288.exfeatures.entities.render.model;

import com.github.halotroop2288.exfeatures.entities.SteveVillagerEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.util.math.MathHelper;

public class SteveVillagerModel extends CompositeEntityModel<SteveVillagerEntity> implements ModelWithHead
{
	private ModelPart head, torso, robe, arms, rightLeg, leftLeg;

	public SteveVillagerModel(float scale, int textureWidth, int textureHeight)
	{
		this.head = (new ModelPart(this)).setTextureSize(textureWidth, textureHeight);
		this.head.setPivot(0.0F, 0.0F, 0.0F);
		this.head.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, scale);
		this.torso = (new ModelPart(this)).setTextureSize(textureWidth, textureHeight);
		this.torso.setPivot(0.0F, 0.0F, 0.0F);
		this.torso.setTextureOffset(16, 20).addCuboid(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, scale);
		this.robe = (new ModelPart(this)).setTextureSize(textureWidth, textureHeight);
		this.robe.setPivot(0.0F, 0.0F, 0.0F);
		this.robe.setTextureOffset(0, 38).addCuboid(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, scale + 0.5F);
		this.torso.addChild(this.robe);
		this.arms = (new ModelPart(this)).setTextureSize(textureWidth, textureHeight);
		this.arms.setPivot(0.0F, 2.0F, 0.0F);
		this.arms.setTextureOffset(44, 22).addCuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, scale);
		this.arms.setTextureOffset(44, 22).addCuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, scale, true);
		this.rightLeg = (new ModelPart(this, 0, 22)).setTextureSize(textureWidth, textureHeight);
		this.rightLeg.setPivot(-2.0F, 12.0F, 0.0F);
		this.rightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
		this.leftLeg = (new ModelPart(this, 0, 22)).setTextureSize(textureWidth, textureHeight);
		this.leftLeg.mirror = true;
		this.leftLeg.setPivot(2.0F, 12.0F, 0.0F);
		this.leftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha)
	{ super.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha); }

	@Override
	public Iterable<ModelPart> getParts()
	{ return ImmutableList.of(this.head, this.torso, this.rightLeg, this.leftLeg, this.arms); }

	@Override
	public void setAngles(SteveVillagerEntity entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch)
	{
		boolean bl = false;
		if (entity instanceof AbstractTraderEntity)
		{ bl = ((AbstractTraderEntity) entity).getHeadRollingTimeLeft() > 0; }
		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
		if (bl)
		{
			this.head.roll = 0.3F * MathHelper.sin(0.45F * customAngle);
			this.head.pitch = 0.4F;
		}
		else
		{
			this.head.roll = 0.0F;
		}
		this.arms.pivotY = 3.0F;
		this.arms.pivotZ = -0.5F;
		this.arms.pitch = -0.0F;
		this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance * 0.5F;
		this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance * 0.5F;
		this.rightLeg.yaw = 0.0F;
		this.leftLeg.yaw = 0.0F;
	}

	@Override
	public ModelPart getHead()
	{ return this.head; }
}
