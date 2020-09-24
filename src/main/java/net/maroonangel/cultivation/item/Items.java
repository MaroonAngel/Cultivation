package net.maroonangel.cultivation.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.maroonangel.cultivation.Cultivation;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class Items {

    public static final Item BASIC_SEEDS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item LUSH_SEEDS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item RICH_SEEDS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item SOUL_SEEDS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item GLOWING_SEEDS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item BEJEWELED_SEEDS = new Item((new Item.Settings().group(Cultivation.GROUP)));


    public static final Item IRON_DUST = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item NITER_DUST = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item GOLD_DUST = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item PLANT_FIBERS = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item PEBBLE = new Item((new Item.Settings().group(Cultivation.GROUP)));
    public static final Item MAGMA_CORE = new Item((new Item.Settings().group(Cultivation.GROUP).rarity(Rarity.RARE).maxCount(16)));
    public static final Item GROWTH_CORE = new Item((new Item.Settings().group(Cultivation.GROUP).rarity(Rarity.RARE).maxCount(16)));
    public static final Item POD_SHELL = new Item((new Item.Settings().group(Cultivation.GROUP)));

    public static final Item COOKED_EGG = new Item((new Item.Settings().group(Cultivation.GROUP).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).build())));
    public static final ItemDrink SWEET_BERRY_JAM = new ItemDrink((new Item.Settings().group(Cultivation.GROUP).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.3F).build())
        .recipeRemainder(net.minecraft.item.Items.GLASS_BOTTLE).maxCount(16)));
    public static final Item JAMMY_BREAD = new Item((new Item.Settings().group(Cultivation.GROUP).food(new FoodComponent.Builder().hunger(5).saturationModifier(0.5F).build())));
    public static final Item CACTUS_SLICE = new Item((new Item.Settings().group(Cultivation.GROUP).food(new FoodComponent.Builder().hunger(3).saturationModifier(0.5F).build())));
    public static final Item CARROT_STEW = new Item((new Item.Settings().group(Cultivation.GROUP).food(new FoodComponent.Builder().hunger(6).saturationModifier(0.6F).build()).recipeRemainder(net.minecraft.item.Items.BOWL)));

    public static final ItemUtility MORTAR_AND_PESTLE = new ItemUtility((new Item.Settings().group(Cultivation.GROUP).maxCount(1).maxDamage(100)));
    public static final ItemUtility KNIFE = new ItemUtility((new Item.Settings().group(Cultivation.GROUP).maxCount(1).maxDamage(100)));
    public static final ItemWateringCan WATERING_CAN = new ItemWateringCan((new Item.Settings().group(Cultivation.GROUP).maxCount(1).maxDamage(100)), false);
    public static final ItemWateringCan DIAMOND_WATERING_CAN = new ItemWateringCan((new Item.Settings().group(Cultivation.GROUP).maxCount(1).maxDamage(300)), true);

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("cultivation", "basic_seeds"), BASIC_SEEDS);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "lush_seeds"), LUSH_SEEDS);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "rich_seeds"), RICH_SEEDS);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "soul_seeds"), SOUL_SEEDS);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "glowing_seeds"), GLOWING_SEEDS);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "bejeweled_seeds"), BEJEWELED_SEEDS);

        Registry.register(Registry.ITEM, new Identifier("cultivation", "iron_dust"), IRON_DUST);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "niter_dust"), NITER_DUST);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "gold_dust"), GOLD_DUST);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "mortar_and_pestle"), MORTAR_AND_PESTLE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "knife"), KNIFE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "plant_fibers"), PLANT_FIBERS);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "pebble"), PEBBLE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "magma_core"), MAGMA_CORE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "growth_core"), GROWTH_CORE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "pod_shell"), POD_SHELL);

        Registry.register(Registry.ITEM, new Identifier("cultivation", "cooked_egg"), COOKED_EGG);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "sweet_berry_jam"), SWEET_BERRY_JAM);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "jammy_bread"), JAMMY_BREAD);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "cactus_slice"), CACTUS_SLICE);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "carrot_stew"), CARROT_STEW);

        Registry.register(Registry.ITEM, new Identifier("cultivation", "watering_can"), WATERING_CAN);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "diamond_watering_can"), DIAMOND_WATERING_CAN);

        FuelRegistry.INSTANCE.add(MAGMA_CORE, 20000);
    }
}
