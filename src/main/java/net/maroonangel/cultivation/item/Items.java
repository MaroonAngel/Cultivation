package net.maroonangel.cultivation.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.maroonangel.cultivation.Cultivation;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final Item FLOUR = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item IRON_DUST = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item MORTAR_AND_PESTLE = new Item((new Item.Settings().group(Cultivation.GROUP).maxCount(1)));
    public static final Item PLANT_FIBERS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    //public static final Item SIEVE = new Item((new Item.Settings().group(Cultivation.GROUP).recipeRemainder(Items.SIEVE).maxCount(1)));
    public static final Item PEBBLE = new Item((new Item.Settings().group(Cultivation.GROUP)));


    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("cultivation", "flour"), FLOUR);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "iron_dust"), IRON_DUST);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "mortar_and_pestle"), MORTAR_AND_PESTLE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "plant_fibers"), PLANT_FIBERS);
        //Registry.register(Registry.ITEM, new Identifier("cultivation", "sieve"), SIEVE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "pebble"), PEBBLE);
    }
}
