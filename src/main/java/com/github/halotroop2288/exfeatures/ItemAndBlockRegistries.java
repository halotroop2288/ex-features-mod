package com.github.halotroop2288.exfeatures;

import com.github.halotroop2288.exfeatures.blocks.CryingObsidianBlock;
import com.github.halotroop2288.exfeatures.blocks.UnstableTNTBlock;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemAndBlockRegistries
{
	/* Unused blocks */
	public static Block UNSTABLE_TNT = new UnstableTNTBlock(FabricBlockSettings.copy(Blocks.TNT).build());
	public static Item UNSTABLE_TNT_ITEM = new BlockItem(UNSTABLE_TNT, new Item.Settings().group(ItemGroup.REDSTONE));
	
	/* Removed/Replaced blocks */
	public static Block RUBY_ORE = new OreBlock(FabricBlockSettings.copy(Blocks.EMERALD_ORE).build());
	public static Item RUBY_ORE_ITEM = new BlockItem(RUBY_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block CRYING_OBSIDIAN = new CryingObsidianBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN).build());
	public static Item CRYING_OBSIDIAN_ITEM = new BlockItem(CRYING_OBSIDIAN, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block PAEONIA = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.PEONY).build());
	public static Item PAEONIA_ITEM = new BlockItem(PAEONIA, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static Block ROSE = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.POPPY).build());
	public static Item ROSE_ITEM = new BlockItem(ROSE, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static Block DIRT_SLAB = new SlabBlock(Block.Settings.copy(Blocks.DIRT));
	public static Item DIRT_SLAB_ITEM = new BlockItem(DIRT_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static Block GRASS_SLAB = new SlabBlock(Block.Settings.copy(Blocks.GRASS_BLOCK));
	public static Item GRASS_SLAB_ITEM = new BlockItem(GRASS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	/* Platform exclusive blocks */
	public static Block CYAN_FLOWER = new FlowerBlock(StatusEffects.INSTANT_HEALTH, 0, FabricBlockSettings.copy(Blocks.POPPY).build());
	public static Item CYAN_FLOWER_ITEM = new BlockItem(CYAN_FLOWER, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static Block GLOWING_OBSIDIAN = new Block(Block.Settings.copy(Blocks.OBSIDIAN))
	{
		public int getLuminance(BlockState state)
		{ return 9; }

		public boolean hasEmissiveLighting(BlockState state)
		{ return true; }
	};
	public static Item GLOWING_OBSIDIAN_ITEM = new BlockItem(GLOWING_OBSIDIAN, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	/* Removed/Replaced items */
	public static Item FIRE = new BlockItem(Blocks.FIRE, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static Item QUIVER = new Item(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
	public static Item RUBY = new Item(new Item.Settings().group(ItemGroup.MISC));
	public static ArmorItem STUDDED_HELMET = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static ArmorItem STUDDED_CHESTPLATE = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static ArmorItem STUDDED_LEGGINGS = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static ArmorItem STUDDED_BOOTS = new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
	public static ArmorItem PLATE_HELMET = new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static ArmorItem PLATE_CHESTPLATE = new ArmorItem(ArmorMaterials.IRON, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	// TODO: Make studded weaker than leather by a little bit, and plate stronger than iron but less than diamond
	
	public static void registerBlocks()
	{
		System.out.println("Readding removed blocks...");
		registerBlock("ruby_ore", RUBY_ORE);
		registerItem("ruby_ore", RUBY_ORE_ITEM);
		System.out.println("Ruby - check.");
		registerBlock("paeonia", PAEONIA);
		registerItem("paeonia", PAEONIA_ITEM);
		System.out.println("Paeonia - check.");
		registerBlock("rose", ROSE);
		registerItem("rose", ROSE_ITEM);
		System.out.println("Rose - check.");
		registerBlock("crying_obsidian", CRYING_OBSIDIAN);
		registerItem("crying_obsidian", CRYING_OBSIDIAN_ITEM);
		System.out.println("Crying Obsidian - check.");
		registerBlock("dirt_slab", DIRT_SLAB);
		registerItem("dirt_slab", DIRT_SLAB_ITEM);
		registerBlock("grass_slab", GRASS_SLAB);
		registerItem("grass_slab", GRASS_SLAB_ITEM);

		System.out.println("Adding other platform-exclusive blocks...");
		
		registerBlock("cyan_flower", CYAN_FLOWER);
		registerItem("cyan_flower", CYAN_FLOWER_ITEM);
		System.out.println("Cyan Flower - check.");
		registerBlock("glowing_obsidian", GLOWING_OBSIDIAN);
		registerItem("glowing_obsidian", GLOWING_OBSIDIAN_ITEM);
		System.out.println("Glowing Obsidian - check.");

		System.out.println("Re-adding unused blocks...");
		
		registerBlock("unstable_tnt", UNSTABLE_TNT);
		registerItem("unstable_tnt", UNSTABLE_TNT_ITEM);
		System.out.println("Unstable TNT - check");
		
	}

	public static void registerItems()
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
		
		System.out.println("Removed items re-added.");
	}

	private static void registerBlock(String id, Block block)
	{ Registry.register(Registry.BLOCK, new Identifier("exfeatures", id), block); }

	private static void registerItem(String id, Item item)
	{ Registry.register(Registry.ITEM, new Identifier("exfeatures", id), item); }
}
