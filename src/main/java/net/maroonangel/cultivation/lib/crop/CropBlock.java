package net.maroonangel.cultivation.lib.crop;

import net.maroonangel.cultivation.Plants;
import net.maroonangel.cultivation.lib.factory.CropBoundingBoxFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;

import java.util.Random;

public class CropBlock extends net.minecraft.block.CropBlock implements BlockEntityProvider {

    private static final EnumProperty<DoubleBlockHalf> HALF;

    static {
        HALF = Properties.DOUBLE_BLOCK_HALF;
    }

    private Identifier itemid;
    private Identifier id;
    private BlockEntityType<CropBlockEntity> entity;
    private VoxelShape[] growthBoundingBoxes;
    private boolean twotall;
    private boolean partialharvest;
    private int tickRate;

    public CropBlock(Settings settings, Identifier id, Identifier itemid) {
        super(settings);
        this.itemid = itemid;
        this.id = id;
        this.twotall = false;
        this.partialharvest = false;
        this.growthBoundingBoxes = CropBoundingBoxFactory.buildBoundingBoxes(new float[]{2, 4, 6, 8, 10, 12, 14, 16});
        this.setDefaultState(this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
        this.tickRate = 25;
    }

    public static void dropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack) {
        if (world instanceof ServerWorld) {
            System.out.println(getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, entity, stack));
            getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, entity, stack).forEach((eachStack) -> {
                int yield = 3;
                eachStack.setCount((eachStack.getCount()*((yield+3)/4)));
                dropStack(world, pos, eachStack);

            });
        }
        state.onStacksDropped((ServerWorld)world, pos, stack);
    }

    public boolean isTwoTall() {
        return this.twotall;
    }

    public CropBlock setTwoTall(boolean twotall) {
        this.twotall = twotall;
        return this;
    }

    public CropBlock setTickRate(int tickrate) {
        this.tickRate = tickrate;
        return this;
    }

    public CropBlock setPartialHarvest(boolean partialHarvest) {
        this.partialharvest = partialHarvest;
        return this;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState state2, WorldView world, BlockPos pos1, BlockPos pos2) {
        DoubleBlockHalf half = this.getBlockHalf(state);
        if (half.equals(DoubleBlockHalf.LOWER)) {
            if (direction.getAxis() == Direction.Axis.Y) {
                // If the block above the bottom block is changed determine if whether or not the blocks are still valid or not
                if (direction == Direction.UP && (state2.getBlock() != state.getBlock() || state2.get(HALF) == half)) {
                    return Blocks.AIR.getDefaultState();
                }
                // If the block under the plant is changed determine if it can still be placed there
                if (direction == Direction.DOWN && !state.canPlaceAt(world, pos1)) {
                    return Blocks.AIR.getDefaultState();
                }
            }
        } else {
            // If the bottom block is removed the top should destroy itself
            if (direction.getAxis() == Direction.Axis.Y && direction == Direction.DOWN && (state2.getBlock() != state.getBlock() || state2.get(HALF) == half)) {
                return Blocks.AIR.getDefaultState();
            }
        }
        // If none of those conditions are met then just return the input state
        return state;

    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        if (this.twotall) {
            BlockPos pos = context.getBlockPos();
            // If the place you wanna place this plant won't fit within the height limit or you can't safely replace the block above it then return null
            if ((pos.getY() >= context.getWorld().getHeight()) || (!context.getWorld().getBlockState(pos.up()).canReplace(context))) {
                return null;
            }
        }
        // Otherwise just return the default state
        return this.getDefaultState();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
        if (this.twotall) {
            world.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 3);
            world.setBlockEntity(pos.up(), world.getBlockEntity(pos));
        }
        world.getBlockTickScheduler().schedule(pos, this, 100);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (this.twotall) {
            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                BlockState state2 = world.getBlockState(pos.down(1));
                return (state2.getBlock() == state.getBlock()) && (state2.get(HALF) == DoubleBlockHalf.LOWER);
            }
        }
        return super.canPlaceAt(state, world, pos);
    }

    public void placeAt(ModifiableWorld world, BlockPos pos, int i) {
        if (this.twotall) {
            world.setBlockState(pos, this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER), i);
            world.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), i);
        }
    }


    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity entity) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (this.twotall) {
            DoubleBlockHalf half = state.get(HALF);
            BlockPos pos2 = (half == DoubleBlockHalf.LOWER ? pos.up(1) : pos.down(1));
            BlockState state2 = world.getBlockState(pos2);

            if (state2.getBlock() == state.getBlock() && state2.get(HALF) != half) {
                world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 35);
                world.syncWorldEvent(entity, 2001, pos2, Block.getRawIdFromState(state2));

                if (!world.isClient && !entity.isCreative()) {
                    dropStacks(state, world, pos, blockEntity, entity, entity.getMainHandStack());
                }
            }

        }
        world.syncWorldEvent(entity, 2001, pos, Block.getRawIdFromState(state));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(new Property[]{HALF});
        super.appendProperties(stateFactory$Builder_1);
    }

    public CropBlock setGrowthBoundingBoxes(VoxelShape[] growthBoundingBoxes) {
        if (growthBoundingBoxes != null) {
            this.growthBoundingBoxes = growthBoundingBoxes;
        }
        return this;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        VoxelShape shape = growthBoundingBoxes[ state.get(this.getAgeProperty()) ];
        if (this.twotall) {
            if (state.get(HALF) == DoubleBlockHalf.UPPER) {
                return shape.offset(0, -1.0, 0);
            } else {
                return shape;
            }
        }
        return shape;
    }


    @Override
    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() {
        return Registry.ITEM.get(this.itemid);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (state.get(AGE) == 7) {
            player.addExhaustion(0.005F);
            if (world instanceof ServerWorld) {
                getDroppedStacks(state, (ServerWorld) world, pos, (BlockEntity) null).forEach((stack) -> {
                    int yield = 3;
                    stack.setCount( (Integer) (stack.getCount() * ((yield + 3)/4) ));
                    if (stack.getItem() == this.getSeedsItem()) {
                        if (this.partialharvest || (stack.getItem() == Plants.ironleaf.getSeed())) {
                            stack.setCount(0);
                        } else {
                            stack.decrement(1);
                        }
                    }
                    dropStack(world, pos, stack);
                });
            }

            int resetAge = this.partialharvest ? 6 : 0;
            world.setBlockState(pos, state.with(AGE, resetAge));
            if (this.twotall) {
                DoubleBlockHalf half2 = (state.get(HALF) == DoubleBlockHalf.UPPER) ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER;
                BlockPos pos2 = (half2 == DoubleBlockHalf.UPPER) ? pos.up(1) : pos.down(1);
                world.setBlockState(pos2, world.getBlockState(pos).with(HALF, half2));
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new CropBlockEntity(this.id);
    }

    public void register(Identifier id) {
        Registry.register(Registry.BLOCK, id, this);
        this.entity = Registry.register(Registry.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.create( () -> new CropBlockEntity(id), this).build(null));
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity entity, ItemStack stack) {
        state = this.isTwoTall() ? Blocks.AIR.getDefaultState() : state;
        super.afterBreak(world, player, pos, state, entity, stack);
    }


    public int getTickRate(WorldView world) {
        return this.tickRate;
    }

    public EnumProperty<DoubleBlockHalf> getBlockHalfProperty() {
        return HALF;
    }

    public DoubleBlockHalf getBlockHalf(BlockState state) {
        return state.get(HALF);
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int newage = this.getAge(state) + this.getGrowthAmount(world);
        int maxage = this.getMaxAge();
        if (newage > maxage) {
            newage = maxage;
        }

        world.setBlockState(pos, world.getBlockState(pos).with(AGE, newage), 2);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld server, BlockPos pos, Random rand) {
        if (this.getBlockHalf(state).equals(DoubleBlockHalf.LOWER)) {
            int age = this.getAge(state);
            if (age < this.getMaxAge()) {
                float moisture = getAvailableMoisture(this, server, pos);
                if (rand.nextInt((int)((float)this.tickRate / moisture) + 1) == 0) {
                    server.setBlockState(pos, this.withAge(age + 1));
                    if (this.twotall) {
                        server.setBlockState(pos.up(), server.getBlockState(pos.up()).with(this.getAgeProperty(), age + 1));
                    }
                }
            }
        }
    }


}
