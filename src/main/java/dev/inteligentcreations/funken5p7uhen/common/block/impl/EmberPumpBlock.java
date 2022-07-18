package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class EmberPumpBlock extends BlockWithEntity
{
    public EmberPumpBlock(Settings settings)
    {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return null;
    }
}
