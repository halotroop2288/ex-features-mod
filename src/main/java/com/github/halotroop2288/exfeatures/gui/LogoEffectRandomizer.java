package com.github.halotroop2288.exfeatures.gui;

import java.util.Random;

public class LogoEffectRandomizer
{
	public double position;
	public double lastTickPosition;
	public double velocity;
	private static final double BLOCK_SPEED = 1; // 1 being normal speed, 0.5 being half speed, 2 being double speed

	public LogoEffectRandomizer(final Random rand, final int xIndex, final int yIndex)
	{
		//		this.position = this.lastTickPosition = (10 + yIndex) + rand.nextDouble() * 32.0D + xIndex; // Alpha
		this.position = this.lastTickPosition = 40 + (rand.nextDouble() * 50D); // Normal
	}

	public void update()
	{
		this.lastTickPosition = this.position;
		if (this.position <= 0)
		{ return; }
		if (this.position > 0.0D)
		{ this.velocity -= 0.6 * BLOCK_SPEED; }
		this.position += this.velocity;
		this.velocity *= 0.9;
		if (this.position <= 0.0D)
		{
			this.position = 0.0D;
			this.velocity = 0.0D;
		}
	}
}