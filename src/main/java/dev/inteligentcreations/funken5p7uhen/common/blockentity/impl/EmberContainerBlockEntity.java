package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.lava.LavaContainerInstance;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.lava.LavaContainerInstanceProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class EmberContainerBlockEntity extends BlockEntity
        implements LavaContainerInstanceProvider
{
    private final LavaContainerInstance INSTANCE;

    public EmberContainerBlockEntity(BlockPos pos,
                                     BlockState state)
    {
        super(BlockEntityInit.EMBER_CONTAINER.get(), pos, state);
        INSTANCE = LavaContainerInstance.createInstance(50,
                50,
                50000,
                new HashMap<>(Map.of(Direction.DOWN, LavaContainerInstance.Action.LAVA_INPUT,
                        Direction.UP, LavaContainerInstance.Action.LAVA_OUTPUT)));
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
    }

    @Override
    protected void writeNbt(NbtCompound nbt)
    {
        super.writeNbt(nbt);
        nbt.putLong("containedLava", INSTANCE.storedLava);
    }

    public static void tick(World world,
                            BlockPos pos,
                            BlockState state,
                            EmberContainerBlockEntity be)
    {
        if (world.isClient()) return;
        Block upperBlock = world.getBlockState(pos.up()).getBlock();
        Block downBlock = world.getBlockState(pos.down()).getBlock();
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
        if (downBlock instanceof BlockEntityProvider)
        {
            BlockEntity blockEntity = world.getBlockEntity(pos.down());
            if (blockEntity instanceof LavaContainerInstanceProvider provider)
            {
                if (be.getInstance().isActionValid(Direction.DOWN, LavaContainerInstance.Action.LAVA_INPUT)
                        && provider.getInstance().isActionValid(Direction.UP, LavaContainerInstance.Action.LAVA_OUTPUT))
                    provider.push(be, provider.getMaxExtract());
                markDirty(world, pos, state);
            }
        }
    }
}
