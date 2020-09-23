package net.maroonangel.cultivation;

import net.maroonangel.cultivation.item.Items;
import net.maroonangel.cultivation.lib.crop.Crop;
import net.maroonangel.cultivation.lib.crop.CropUnfertilizable;
import net.maroonangel.cultivation.lib.factory.CropBoundingBoxFactory;
import net.minecraft.item.FoodComponent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.maroonangel.cultivation.Cultivation.MODID;

public class Plants {

    public static final Crop cotton = new Crop(new Identifier(MODID, "cotton"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 4, 6, 10, 12, 14, 18, 20} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(true)
            .setTwoTall(true)
            .build();

    public static final Crop ironleaf = new Crop(new Identifier(MODID, "ironleaf"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(false)
            .setItem(Items.IRON_DUST)
            .setSeedRarity(Rarity.UNCOMMON)
            .setTickRate(15)
            .build();

    public static final Crop raingrass = new Crop(new Identifier(MODID, "raingrass"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(false)
            .setItem(net.minecraft.item.Items.LAPIS_LAZULI)
            .setTickRate(15)
            .build();

    public static final Crop volcapod = new Crop(new Identifier(MODID, "volcapod"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(false)
            .setItem(Items.MAGMA_CORE)
            .setSeedRarity(Rarity.UNCOMMON)
            .setTickRate(40)
            .build();

    public static final Crop grovepod = new Crop(new Identifier(MODID, "grovepod"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(false)
            .setItem(Items.GROWTH_CORE)
            .setSeedRarity(Rarity.UNCOMMON)
            .setTickRate(40)
            .build();

    public static final Crop gemtree = new Crop(new Identifier(MODID, "gemtree"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(false)
            .setItem(net.minecraft.item.Items.EMERALD)
            .setSeedRarity(Rarity.RARE)
            .setTickRate(35)
            .build();

    public static final CropUnfertilizable crystalis = new CropUnfertilizable(new Identifier(MODID, "crystalis"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(false)
            .setItem(net.minecraft.item.Items.DIAMOND)
            .setSeedRarity(Rarity.RARE)
            .setTickRate(60)
            .build();

    public static final Crop slimedrop = new Crop(new Identifier(MODID, "slimedrop"))
            .setItemGroup(Cultivation.GROUP)
            .setCropGrowthBoundingBoxes(CropBoundingBoxFactory.buildBoundingBoxes( new float[]{1, 2, 3, 4, 5, 6, 7, 8} ))
            .setSeperateSeed(true)
            .setSeedGroup(Cultivation.GROUP)
            .setPartialHarvest(false)
            .setItem(net.minecraft.item.Items.SLIME_BALL)
            .setTickRate(25)
            .build();

    public static void register() {
        cotton.register();
        ironleaf.register();
        raingrass.register();
        volcapod.register();
        grovepod.register();
        gemtree.register();
        crystalis.register();
        slimedrop.register();
    }
}
