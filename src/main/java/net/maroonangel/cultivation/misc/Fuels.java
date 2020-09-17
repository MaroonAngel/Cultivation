package net.maroonangel.cultivation.misc;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.maroonangel.cultivation.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Fuels {

    public static void register() {
        FuelRegistry.INSTANCE.add(Items.MAGMA_CORE, 20000);
        FuelRegistry.INSTANCE.add(Items.PLANT_FIBERS, 50);

    }
}
