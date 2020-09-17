package net.maroonangel.cultivation.lib.crop;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import java.util.Random;

public class CropBlockEntity extends BlockEntity implements Tickable {

    private Identifier id;

    private int yield = 1;
    private int growth = 1;

    public CropBlockEntity(Identifier id) {
        super(Registry.BLOCK_ENTITY_TYPE.get(id));
        this.id = id;
    }

    public int getYield() {
        return this.yield;
    }

    public int getGrowth() {
        return this.growth;
    }

    public void setYield(int yield) {
        if (yield > 5) {
            this.yield = 5;
        } else {
            this.yield = yield;
        }
    }

    public void setGrowth(int growth) {
        if (growth > 5) {
            this.growth = 5;
        } else {
            this.growth = growth;
        }
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        CompoundTag genes = new CompoundTag();
        genes.putInt("yield", this.yield);
        genes.putInt("growth", this.growth);
        return tag;
    }

    public void fromTag(CompoundTag tag, BlockState state) {
        super.fromTag(state, tag);
        CompoundTag genes = tag.getCompound("Genes");
        this.yield = genes.getInt("yield");
        this.growth = genes.getInt("growth");
    }

    public void tick() {
        world = this.getWorld();

        if (world.getBlockState(this.getPos()).getBlock() instanceof CropBlock) {
            CropBlock block = (CropBlock) world.getBlockState(this.getPos()).getBlock();
            if (block.isTwoTall()) {
                BlockState state = world.getBlockState(this.getPos());
                BlockPos other = state.get(block.getBlockHalfProperty()) == DoubleBlockHalf.LOWER ? this.getPos().up() : this.getPos().down(1);
                if (world.getBlockState(other).getBlock() instanceof CropBlock) {
                    if (world.getBlockState(other).get(block.getAgeProperty()) > state.get(block.getAgeProperty())) {
                        world.setBlockState(this.getPos(), world.getBlockState(other).with(block.getBlockHalfProperty(), state.get(block.getBlockHalfProperty())));
                    } else {
                        world.setBlockState(other, world.getBlockState(this.getPos()).with(block.getBlockHalfProperty(), world.getBlockState(other).get(block.getBlockHalfProperty())));
                    }
                }
            }
        }

    }
}

