package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dev.inteligentcreations.funken5p7uhen.common.block.impl.EmberPumpBlock;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.lava.LavaContainerInstance;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.lava.LavaContainerInstanceProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmberPumpBlockEntity extends BlockEntity
        implements LavaContainerInstanceProvider
{
    private final LavaContainerInstance INSTANCE;
    public long blazeEnergy;

    private final long maxEnergy = 5000;

    public static final Map<Item, Long> CHARGEABLE = Maps.newHashMap(Map.of(
            Items.BLAZE_ROD, 600L,
            Items.FLINT_AND_STEEL, 1000L,
            Items.BLAZE_POWDER, 300L,
            Items.FIRE_CHARGE, 1000L
    ));

    public EmberPumpBlockEntity(BlockPos pos,
                                BlockState state)
    {
        super(BlockEntityInit.EMBER_PUMP.get(), pos, state);
        INSTANCE = LavaContainerInstance.createInstance(0,
                10,
                1000,
                new HashMap<>(Map.of(Direction.UP,
                        LavaContainerInstance.Action.LAVA_OUTPUT)));
        this.blazeEnergy = 0;
    }

    @Override
    public LavaContainerInstance getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void readNbt(NbtCompound nbt)
    {
        super.readNbt(nbt);
        INSTANCE.storedLava = nbt.getLong("containedLava");
        blazeEnergy = nbt.getLong("storedBlazeEnergy");
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        nbt.putLong("containedLava", INSTANCE.storedLava);
        nbt.putLong("storedBlazeEnergy", blazeEnergy);
    }

    public static void tick(World world,
                            BlockPos pos,
                            BlockState state,
                            EmberPumpBlockEntity be)
    {
        if (world.isClient()) return;
        if (be.blazeEnergy > 0)
        {
            state = state.with(EmberPumpBlock.CHARGED, true);
        }
        else
        {
            state = state.with(EmberPumpBlock.CHARGED, false);
        }
        world.setBlockState(pos, state);
        markDirty(world, pos, state);
        if (be.blazeEnergy > be.maxEnergy)
        {
            be.blazeEnergy = be.maxEnergy;
            markDirty(world, pos, state);
        }
        if (be.blazeEnergy < 0)
        {
            be.blazeEnergy = 0;
            markDirty(world, pos, state);
        }
        Block upperBlock = world.getBlockState(pos.up()).getBlock();
        if (upperBlock instanceof BlockEntityProvider)
        {
            BlockEntity blockEntity = world.getBlockEntity(pos.up());
            if (blockEntity instanceof LavaContainerInstanceProvider provider)
            {
                if (be.getInstance().isActionValid(Direction.UP, LavaContainerInstance.Action.LAVA_OUTPUT)
                        && provider.getInstance().isActionValid(Direction.DOWN, LavaContainerInstance.Action.LAVA_INPUT))
                    be.push(provider, be.getMaxExtract());
                markDirty(world, pos, state);
            }
        }
        List<Block> surroundingBlocks = Lists.newArrayList(
                world.getBlockState(pos.down()).getBlock(),
                world.getBlockState(pos.north()).getBlock(),
                world.getBlockState(pos.south()).getBlock(),
                world.getBlockState(pos.east()).getBlock(),
                world.getBlockState(pos.west()).getBlock()
        );
        for (Block block : surroundingBlocks)
        {
            if (block instanceof FluidBlock fluidBlock)
            {
                FlowableFluid fluid = fluidBlock.getFluid();
                if (fluid instanceof LavaFluid.Still)
                {
                    if (be.blazeEnergy > 0)
                    {
                        be.blazeEnergy -= 1;
                        be.add(1);
                        markDirty(world, pos, state);
                    }
                }
            }
        }
    }

    public long getMaxEnergy()
    {
        return maxEnergy;
    }

    public static void beMarkDirty(World world,
                                   BlockPos pos,
                                   BlockState state)
    {
        markDirty(world, pos, state);
    }
}
