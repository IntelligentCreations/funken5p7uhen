package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BasaltPiglinSculptureBlock extends HorizontalFacingBlock
{
    public BasaltPiglinSculptureBlock(Settings arg)
    {
        super(arg);
        setDefaultState(this.stateManager.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,
                                      BlockView view,
                                      BlockPos pos,
                                      ShapeContext ctx)
    {
        return VoxelShapes.cuboid(0.125, 0, 0.125,
                0.875, 2, 0.875);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
}
