package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.LavaContainerInstance;
import dev.inteligentcreations.funken5p7uhen.common.util.instance.LavaContainerInstanceProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class EmberContainerBlockEntity extends BlockEntity implements LavaContainerInstanceProvider
{
    private static final LavaContainerInstance INSTANCE = LavaContainerInstance.createInstance(500, 500, 100000);

    public EmberContainerBlockEntity(BlockPos pos,
                                     BlockState state)
    {
        super(BlockEntityInit.EMBER_CONTAINER.get(), pos, state);
    }

    @Override
    public LavaContainerInstance getLavaContainerInstance()
    {
        return INSTANCE;
    }
}
