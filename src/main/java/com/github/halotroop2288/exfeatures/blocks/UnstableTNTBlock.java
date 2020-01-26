package com.github.halotroop2288.exfeatures.blocks;

import net.minecraft.block.TntBlock;

public class UnstableTNTBlock extends TntBlock
{
	public UnstableTNTBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getDefaultState().with(UNSTABLE, true));
	}
}
