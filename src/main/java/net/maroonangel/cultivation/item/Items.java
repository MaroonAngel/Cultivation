package net.maroonangel.cultivation.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.maroonangel.cultivation.Cultivation;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final Item FLOUR = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item IRON_DUST = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item NITER_DUST = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item GOLD_DUST = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item MORTAR_AND_PESTLE = new Item((new Item.Settings().group(Cultivation.GROUP).maxCount(1)));
    public static final Item PLANT_FIBERS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item PEBBLE = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item MAGMA_CORE = new Item((new Item.Settings().group(Cultivation.GROUP).rarity(Rarity.RARE).maxCount(16)));
    public static final Item POD_SHELL = new Item((new Item.Settings().group(Cultivation.GROUP)));

    public static final Item COOKED_EGG = new Item((new Item.Settings().group(Cultivation.GROUP).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.5F).build())));


    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("cultivation", "flour"), FLOUR);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "iron_dust"), IRON_DUST);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "niter_dust"), NITER_DUST);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "gold_dust"), GOLD_DUST);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "mortar_and_pestle"), MORTAR_AND_PESTLE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "plant_fibers"), PLANT_FIBERS);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "pebble"), PEBBLE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "magma_core"), MAGMA_CORE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "pod_shell"), POD_SHELL);
        FuelRegistry.INSTANCE.add(MAGMA_CORE, 20000);

        Registry.register(Registry.ITEM, new Identifier("cultivation", "cooked_egg"), COOKED_EGG);
    }
}
