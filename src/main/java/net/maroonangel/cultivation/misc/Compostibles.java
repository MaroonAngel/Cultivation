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
        registerCompostableItem(1F, Plants.ironleaf.getSeed());
    }

    public static void registerCompostableItem(float chance, ItemConvertible item)
    {
        if (item.asItem() != net.minecraft.item.Items.AIR)
        {
            ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), chance);
        }
    }
}
