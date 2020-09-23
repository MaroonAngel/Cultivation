package net.maroonangel.cultivation.block;

import com.mojang.datafixers.util.Pair;
import net.maroonangel.cultivation.item.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;

public class SieveBlock extends Block {

    private List<Pair<Item, Pair<Item, Integer>>> usable = new ArrayList<Pair<Item, Pair<Item, Integer>>>();


    public SieveBlock(Settings settings) {
        super(settings);
        usable.add(new Pair<Item, Pair<Item, Integer>>(net.minecraft.item.Items.DIRT, new Pair<Item, Integer>(Items.PEBBLE, 3)));
        usable.add(new Pair<Item, Pair<Item, Integer>>(net.minecraft.item.Items.GRAVEL, new Pair<Item, Integer>(net.minecraft.item.Items.DIRT, 1)));
    }

    private Pair<Item, Integer> isUsable(Item item) {
        for (Pair<Item, Pair<Item, Integer>> pair : usable) {
            if (pair.getFirst() == item) {
                return pair.getSecond();
            }
        }
        return null;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(1/16f, 0f, 1/16f, 15/16f, 3/16f, 15/16f);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (isUsable(itemStack.getItem()) != null) {
            world.playSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            if (!player.abilities.creativeMode) {
                itemStack.decrement(1);
            }
            //dropStack(world, pos, new ItemStack(Items.PEBBLE, world.random.nextInt(3)));
            dropStack(world, pos, new ItemStack(isUsable(itemStack.getItem()).getFirst().asItem(), isUsable(itemStack.getItem()).getSecond()));

            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    private Item getDroppedItem(ItemStack itemStack) {
        return usable.get(usable.indexOf(itemStack)).getFirst().asItem();
    }

}
