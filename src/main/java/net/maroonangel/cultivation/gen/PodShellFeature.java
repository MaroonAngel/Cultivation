package net.maroonangel.cultivation.gen;

import com.mojang.serialization.Codec;
import net.maroonangel.cultivation.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class PodShellFeature extends Feature<DefaultFeatureConfig> {
    public PodShellFeature(Codec<DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos,
                            DefaultFeatureConfig config) {
        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        Direction offset = Direction.NORTH;

        world.setBlockState(topPos, Blocks.POD_SHELL.getDefaultState(), 3);
        return true;
    }
}



