package net.maroonangel.cultivation.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.maroonangel.cultivation.Plants;
import net.maroonangel.cultivation.block.Blocks;
import net.maroonangel.cultivation.lib.crop.CropBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer {

    public static List<Block> blocksToRender = new ArrayList<Block>();

    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(Plants.cotton.getBlock(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Plants.ironleaf.getBlock(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Plants.raingrass.getBlock(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Plants.grovepod.getBlock(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Plants.volcapod.getBlock(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Plants.gemtree.getBlock(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Plants.crystalis.getBlock(), RenderLayer.getCutout());


        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SIEVE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.POD_SHELL, RenderLayer.getCutout());
    }

}
