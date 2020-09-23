package net.maroonangel.cultivation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.maroonangel.cultivation.block.Blocks;
import net.maroonangel.cultivation.gen.Features;
import net.maroonangel.cultivation.misc.Compostibles;
import net.maroonangel.cultivation.misc.Fuels;
import net.maroonangel.cultivation.misc.LootTables;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.maroonangel.cultivation.item.*;


public class Cultivation implements ModInitializer {

	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier("cultivation", "general"), () -> new ItemStack(Plants.cotton.getItem()));
	public static final String MODID = "cultivation";



	@Override
	public void onInitialize() {
		Plants.register();
		Items.register();
		LootTables.register();
		Compostibles.register();
		Blocks.register();
		Fuels.register();
		Features.register();
	}
}
