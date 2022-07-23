package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CustomShapedBlock extends Block
{
    private final VoxelShape shape;

    public CustomShapedBlock(Settings arg, VoxelShape shape)
    {
        super(arg);
        this.shape = shape;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,
                                      BlockView world,
                                      BlockPos pos,
                                      ShapeContext context)
    {
        return shape;
    }
}
