package net.maroonangel.cultivation.misc;

import net.maroonangel.cultivation.Plants;
import net.maroonangel.cultivation.item.Items;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.registry.Registry;

public class Compostibles {

    public static void register() {
        registerCompostableItem(0.4F, Items.PLANT_FIBERS);
        registerCompostableItem(0.25F, Plants.cotton.getSeed());
        registerCompostableItem(0.6F, Plants.ironleaf.getSeed());
        registerCompostableItem(0.65F, Plants.raingrass.getSeed());
        registerCompostableItem(0.7F, Plants.volcapod.getSeed());
        registerCompostableItem(0.7F, Plants.grovepod.getSeed());
        registerCompostableItem(0.7F, Plants.gemtree.getSeed());
        registerCompostableItem(0.1F, Plants.crystalis.getSeed());
        registerCompostableItem(0.7F, Plants.slimedrop.getSeed());
        registerCompostableItem(0.7F, Plants.piglins_bounty.getSeed());
        registerCompostableItem(0.7F, Plants.quartzglass.getSeed());
        registerCompostableItem(0.7F, Plants.blazehearth.getSeed());
    }

    public static void registerCompostableItem(float chance, ItemConvertible item)
    {
        if (item.asItem() != net.minecraft.item.Items.AIR)
        {
            ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), chance);
        }
    }
}

