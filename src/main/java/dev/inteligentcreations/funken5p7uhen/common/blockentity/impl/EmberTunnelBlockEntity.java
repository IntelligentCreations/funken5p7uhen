package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import dev.inteligentcreations.funken5p7uhen.common.block.impl.EmberTunnelBlock;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.LavaContainerInstance;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.LavaContainerInstanceProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class EmberTunnelBlockEntity extends BlockEntity implements LavaContainerInstanceProvider
{
    private final LavaContainerInstance INSTANCE;

    public EmberTunnelBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EMBER_TUNNEL.get(), pos, state);
        INSTANCE = LavaContainerInstance.createInstance(1,
                1,
                100,
                new HashMap<>(Map.of(state.get(Properties.FACING), LavaContainerInstance.Action.LAVA_OUTPUT,
                        state.get(Properties.FACING).getOpposite(), LavaContainerInstance.Action.LAVA_INPUT)));
    }

    @Override
    public LavaContainerInstance getLavaContainerInstance() {
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
                            EmberTunnelBlockEntity be)
    {
        if (world.isClient()) return;
        Block facingBlock = world.getBlockState(pos.offset(state.get(Properties.FACING))).getBlock();
        Block facingBlockOpposite = world.getBlockState(pos.offset(state.get(Properties.FACING).getOpposite())).getBlock();
        if (be.getLavaContainerInstance().storedLava > 0)
        {
            state = state.with(EmberTunnelBlock.WITH_LAVA, true);
            world.setBlockState(pos, state);
            markDirty(world, pos, state);
        }
        else
        {
            state = state.with(EmberTunnelBlock.WITH_LAVA, false);
            world.setBlockState(pos, state);
            markDirty(world, pos, state);
        }
        if (facingBlock instanceof BlockEntityProvider)
        {
            BlockEntity blockEntity = world.getBlockEntity(pos.offset(state.get(Properties.FACING)));
            if (blockEntity instanceof LavaContainerInstanceProvider provider)
            {
                if (be.getLavaContainerInstance().isActionValid(state.get(Properties.FACING), LavaContainerInstance.Action.LAVA_OUTPUT)
                        && provider.getLavaContainerInstance().isActionValid(state.get(Properties.FACING).getOpposite(), LavaContainerInstance.Action.LAVA_INPUT))
                    be.push(provider, be.getMaxExtract());
                markDirty(world, pos, state);
            }
        }
        if (facingBlockOpposite instanceof BlockEntityProvider)
        {
            BlockEntity blockEntity = world.getBlockEntity(pos.offset(state.get(Properties.FACING).getOpposite()));
            if (blockEntity instanceof LavaContainerInstanceProvider provider)
            {
                if (provider.getLavaContainerInstance().isActionValid(state.get(Properties.FACING), LavaContainerInstance.Action.LAVA_OUTPUT)
                        && be.getLavaContainerInstance().isActionValid(state.get(Properties.FACING).getOpposite(), LavaContainerInstance.Action.LAVA_INPUT))
                    provider.push(be, provider.getMaxExtract());
                markDirty(world, pos, state);
            }
        }
    }
}
