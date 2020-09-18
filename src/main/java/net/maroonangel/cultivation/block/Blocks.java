package net.maroonangel.cultivation.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.maroonangel.cultivation.Cultivation;
import net.maroonangel.cultivation.lib.crop.CropBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class Blocks {

    public static final Block SIEVE_BLOCK = new SieveBlock(FabricBlockSettings.of(Material.WOOD).hardness(0.6f).nonOpaque());
    public static final Block POD_SHELL = new PodShellBlock(FabricBlockSettings.of(Material.PLANT).hardness(0.3f).nonOpaque().noCollision());
    //public static final Block CHEESE_BLOCK = new Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.6f));


    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier("cultivation", "sieve_block"), SIEVE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("cultivation", "sieve_block"), new BlockItem(SIEVE_BLOCK, new Item.Settings().group(Cultivation.GROUP)));

        Registry.register(Registry.BLOCK, new Identifier("cultivation", "pod_shell"), POD_SHELL);
    }


}
