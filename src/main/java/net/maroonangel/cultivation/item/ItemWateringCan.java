package net.maroonangel.cultivation.item;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.maroonangel.cultivation.lib.crop.Crop;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
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
    public ItemWateringCan(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        Block block = state.getBlock();

        if(player != null && block != Blocks.AIR && context.getHand() == Hand.MAIN_HAND) {

            // particles
            if (world.isClient) {
                if (block instanceof CropBlock) {
                    CropBlock crop = (CropBlock)block;
                    if (context.getStack().getDamage() < context.getStack().getMaxDamage() && crop.isFertilizable(world, pos, state, true )) {
                        for (int i = 1; i < 10; i++) {
                            double xVel = (world.random.nextInt(100) / 100f) - 0.5f;
                            double yVel = (world.random.nextInt(100) / 100f);
                            double zVel = (world.random.nextInt(100) / 100f) - 0.5f;

                            double xOff = (world.random.nextInt(100) / 100f) - 0.5f;
                            double zOff = (world.random.nextInt(100) / 100f) - 0.5f;


                            world.addParticle(ParticleTypes.RAIN, pos.getX() + 0.5 + xOff, pos.getY() + 0.5, pos.getZ() + 0.5 + zOff, xVel*2, yVel*3, zVel*2);
                        }
                    }
                }
            }


            if(!world.isClient) {
                // refill can
                if (context.getStack().getDamage() != 0) {
                    HitResult ray = player.raycast(3, 0, true);
                    BlockPos pos1 = new BlockPos(ray.getPos().x, ray.getPos().y, ray.getPos().z);
                    BlockState state1 = world.getBlockState(pos1);
                    if (state1.getBlock() == Blocks.WATER) {
                        ItemStack stack = context.getStack();
                        stack.setDamage(stack.getDamage() - 2);
                        return ActionResult.SUCCESS;
                    }
                }


                // water crops
                if (block instanceof CropBlock) {
                    CropBlock crop = (CropBlock)block;
                    if (context.getStack().getDamage() == context.getStack().getMaxDamage() || !crop.isFertilizable(world, pos, state, false ))
                        return ActionResult.FAIL;

                    crop.randomTick(state, (ServerWorld)world, pos, world.random);
                    ItemStack stack = context.getStack();
                    stack.setDamage(stack.getDamage() + 1);
                    return ActionResult.SUCCESS;
                }

            }

            //player.playSound(state.getSoundGroup().getPlaceSound(), state.getSoundGroup().getVolume(), state.getSoundGroup().getPitch());
        }

        return ActionResult.FAIL;
    }






    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        HitResult ray = user.raycast(3, 0, true);
        BlockPos pos = new BlockPos(ray.getPos().x, ray.getPos().y, ray.getPos().z);
        BlockState state = world.getBlockState(pos);
        //if (state.getBlock() == Blocks.WATER)
        //    System.out.println("yay!");


        ItemStack stack = user.getStackInHand(hand);
        if (stack.getDamage() == stack.getMaxDamage())
            return TypedActionResult.fail(user.getStackInHand(hand));
        else
            stack.setDamage(stack.getDamage() + 1);


        return TypedActionResult.pass(user.getStackInHand(hand));
    }

}
