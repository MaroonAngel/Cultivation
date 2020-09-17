package net.maroonangel.cultivation.init;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.maroonangel.cultivation.block.Blocks;
import net.minecraft.client.render.RenderLayer;

public class ClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SIEVE_BLOCK, RenderLayer.getCutout());
    }

}
