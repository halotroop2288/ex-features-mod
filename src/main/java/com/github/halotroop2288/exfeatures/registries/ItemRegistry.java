package com.github.halotroop2288.exfeatures.registries;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry
{
	public static Item FIRE = new BlockItem(Blocks.FIRE, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static Item QUIVER = new Item(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static Item RUBY = new Item(new Item.Settings().group(ItemGroup.MISC));
	public static Item STUDDED_HELMET = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static Item STUDDED_CHESTPLATE = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static Item STUDDED_LEGGINGS = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static Item STUDDED_BOOTS = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
	public static Item PLATE_HELMET = new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static Item PLATE_CHESTPLATE = new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static Item PIGMAN_SPAWN_EGG = new SpawnEggItem(EntityRegistry.PIGMAN, 0xFFC0CB, 0xFF1493, new Item.Settings().group(ItemGroup.MISC));
	public static Item STEVE_VILLAGER_SPAWN_EGG = new SpawnEggItem(EntityRegistry.STEVE_VILLAGER, 0x000000, 0xFFFFFF, new Item.Settings().group(ItemGroup.MISC));
	// TODO: Make studded weaker than leather by a little bit, and plate stronger than iron but less than diamond

	public static void registerItems(boolean doIt)
	{
		if (doIt)
		{
			registerItem("quiver", QUIVER);
			registerItem("ruby", RUBY);
			registerItem("fire", FIRE);
			registerItem("studded_helmet", STUDDED_HELMET);
			registerItem("studded_chestplate", STUDDED_CHESTPLATE);
			registerItem("studded_leggings", STUDDED_LEGGINGS);
			registerItem("studded_boots", STUDDED_BOOTS);
			registerItem("plate_helmet", PLATE_HELMET);
			registerItem("plate_chestplate", PLATE_CHESTPLATE);
			registerItem("pigman_spawn_egg", PIGMAN_SPAWN_EGG);
			registerItem("steve_villager_spawn_egg", STEVE_VILLAGER_SPAWN_EGG);
		}
	}
	
	public static void registerItem(String id, Item item)
	{ Registry.register(Registry.ITEM, new Identifier("exfeatures", id), item); }
	
}
