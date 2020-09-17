package net.maroonangel.cultivation.misc;

import java.util.List;

import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.maroonangel.cultivation.Plants;
import net.maroonangel.cultivation.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;

public class LootTables {
    private static final List<LootTableInsert> INSERTS = Lists.newArrayList();


    public static void register()
    {
        FabricLootPoolBuilder IRONLEAF_SEEDS = FabricLootPoolBuilder.builder()
                .rolls(ConstantLootTableRange.create(1))
                .with(ItemEntry.builder(Plants.ironleaf.getSeed()))
                .withCondition(RandomChanceLootCondition.builder(0.0125F).build());

        insert(new LootTableInsert(IRONLEAF_SEEDS,
                new Identifier("minecraft", "blocks/grass")
        ));

        FabricLootPoolBuilder COTTON_SEEDS = FabricLootPoolBuilder.builder()
                .rolls(ConstantLootTableRange.create(1))
                .with(ItemEntry.builder(Plants.cotton.getSeed()))
                .withCondition(RandomChanceLootCondition.builder(0.02F).build());

        insert(new LootTableInsert(COTTON_SEEDS,
                new Identifier("minecraft", "blocks/grass")
        ));

        FabricLootPoolBuilder PLANT_FIBERS = FabricLootPoolBuilder.builder()
                .rolls(ConstantLootTableRange.create(1))
                .with(ItemEntry.builder(Items.PLANT_FIBERS))
                .withCondition(RandomChanceLootCondition.builder(0.75F).build());

        insert(new LootTableInsert(PLANT_FIBERS,
                new Identifier("minecraft", "blocks/grass")
        ));
        insert(new LootTableInsert(PLANT_FIBERS,
                new Identifier("minecraft", "blocks/grass")
        ));
        insert(new LootTableInsert(PLANT_FIBERS,
                new Identifier("minecraft", "blocks/grass")
        ));

        // Perform Callback insertion
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
            INSERTS.forEach(i->{
                if(ArrayUtils.contains(i.tables, identifier))
                {
                    i.insert(supplier);
                }
            });
        }));
    }

    public static void insert(LootTableInsert insert)
    {
        INSERTS.add(insert);
    }

    public static class LootTableInsert
    {
        public final Identifier[] tables;
        public final FabricLootPoolBuilder lootPool;

        public LootTableInsert(FabricLootPoolBuilder lootPool, Identifier... tables)
        {
            this.tables = tables;
            this.lootPool = lootPool;
        }

        public void insert(FabricLootSupplierBuilder supplier)
        {
            supplier.pool(lootPool);
        }
    }




}
