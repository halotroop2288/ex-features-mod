package com.github.halotroop2288.exfeatures.registries;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundRegistry
{
	private static Identifier WOLF_HOWL_ID = new Identifier("exfeatures", "mob.wolf.howl");
	public static SoundEvent ENTITY_WOLF_HOWL = new SoundEvent(WOLF_HOWL_ID);

	private static void registerSound(Identifier id, SoundEvent event)
	{ Registry.register(Registry.SOUND_EVENT, id, event); }

	public static void registerSounds()
	{
		registerSound(WOLF_HOWL_ID, ENTITY_WOLF_HOWL);
		System.out.println("Unused sounds readded.");
	}
}
