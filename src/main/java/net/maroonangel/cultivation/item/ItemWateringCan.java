package net.maroonangel.cultivation.item;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.maroonangel.cultivation.lib.crop.Crop;
import net.maroonangel.cultivation.lib.factory.CropBoundingBoxFactory;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ItemWateringCan extends Item {

    private boolean diamond;


    public ItemWateringCan(Settings settings, boolean diamond) {
        super(settings);
        this.diamond = diamond;
    }

    private boolean canWater(Block block, World world, BlockPos pos, ItemUsageContext ctx) {
        if (block instanceof CropBlock) {
            CropBlock crop = (CropBlock) block;
            if ((crop.isFertilizable(world, pos, world.getBlockState(pos), true) || this.diamond) &&
                    ctx.getStack().getDamage() < ctx.getStack().getMaxDamage()) {
                return true;
            }
        }
        return false;
    }

    private void waterCrop(CropBlock crop) {

    }

    private void createParticles(World world, BlockPos newPos) {
        for (int i = 1; i < 10; i++) {
            double xVel = (world.random.nextInt(100) / 100f) - 0.5f;
            double yVel = (world.random.nextInt(100) / 100f);
            double zVel = (world.random.nextInt(100) / 100f) - 0.5f;

            double xOff = (world.random.nextInt(100) / 100f) - 0.5f;
            double zOff = (world.random.nextInt(100) / 100f) - 0.5f;

            //player.playSound(SoundEvents.WEATHER_RAIN, 0.3f, 10);
            world.addParticle(ParticleTypes.RAIN, newPos.getX() + 0.5 + xOff, newPos.getY() + 0.5, newPos.getZ() + 0.5 + zOff, xVel*2, yVel*3, zVel*2);
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        Block block = state.getBlock();

        boolean youWin = false;

        if(player != null && context.getHand() == Hand.MAIN_HAND) {

            if (!diamond) {
                if (!world.isClient) {
                    if (block instanceof CropBlock && block != Blocks.AIR) {
                        CropBlock crop = (CropBlock) block;
                        if (canWater(crop, world, pos, context)) {
                            // Water crop
                            crop.randomTick(state, (ServerWorld) world, pos, world.random);
                            youWin = true;
                        }
                    }
                } else  {
                    if ((block instanceof CropBlock && canWater(block, world, pos, context))|| (block == Blocks.AIR && context.getStack().getDamage() < context.getStack().getMaxDamage())) {
                        createParticles(world, pos);
                    }
                }
            }
            else {
                if (!world.isClient) {
                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                            Block bl = world.getBlockState(newPos).getBlock();
                            if (bl instanceof CropBlock && bl != Blocks.AIR) {
                                CropBlock crop = (CropBlock) bl;
                                if (canWater(crop, world, newPos, context)) {
                                    // Water crop
                                    if (world.random.nextInt(3) == 0)
                                        crop.randomTick(state, (ServerWorld) world, newPos, world.random);
                                    youWin = true;
                                }
                            }
                        }
                    }
                } else  {
                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                            Block bl = world.getBlockState(newPos).getBlock();
                            if (bl instanceof CropBlock || bl == Blocks.AIR) {
                                if (canWater(bl, world, pos, context) || (bl == Blocks.AIR && context.getStack().getDamage() < context.getStack().getMaxDamage()) )
                                    createParticles(world, newPos);
                            }
                        }
                    }
                }
            }

            if (youWin) {
                ItemStack stack = context.getStack();
                stack.setDamage(stack.getDamage() + 1);
                return ActionResult.SUCCESS;
            }


            if(!world.isClient && block != Blocks.AIR) {
                // refill can
                if (context.getStack().getDamage() != 0) {
                    HitResult ray = player.raycast(3, 0, true);
                    BlockPos pos1 = new BlockPos(ray.getPos().x, ray.getPos().y, ray.getPos().z);
                    BlockState state1 = world.getBlockState(pos1);
                    if (state1.getBlock() == Blocks.WATER) {
                        ItemStack stack = context.getStack();
                        int repair = this.diamond ? 4 : 2;
                        stack.setDamage(stack.getDamage() - repair);
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }

        return ActionResult.FAIL;
    }
}
