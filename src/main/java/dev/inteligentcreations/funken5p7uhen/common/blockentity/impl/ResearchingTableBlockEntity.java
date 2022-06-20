package dev.inteligentcreations.funken5p7uhen.common.blockentity.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ResearchingTableBlockEntity extends BlockEntity
{
    public ResearchingTableBlockEntity(BlockPos pos,
                                       BlockState state)
    {
        super(BlockEntityInit.RESEARCHING_TABLE.get(), pos, state);
    }
}
