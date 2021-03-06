package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class ThinPillarBlock extends Block
{
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = Properties.FACING;

    public ThinPillarBlock(Settings arg)
    {
        super(arg);
        setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add(FACING);
        stateManager.add(WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,
                                      BlockView view,
                                      BlockPos pos,
                                      ShapeContext ctx)
    {
        Direction dir = state.get(FACING);
        return switch (dir)
                {
                    case NORTH, SOUTH -> VoxelShapes.cuboid(0.1875f, 0.1875f, 0.0f, 0.8125f, 0.8125f, 1.0f);
                    case EAST, WEST -> VoxelShapes.cuboid(0.0f, 0.1875f, 0.1875f, 1.0f, 0.8125f, 0.8125f);
                    case UP, DOWN -> VoxelShapes.cuboid(0.1875f, 0.0f, 0.1875f, 0.8125f, 1.0f, 0.8125f);
                };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerLookDirection().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state,
                                                Direction direction,
                                                BlockState neighborState,
                                                WorldAccess world,
                                                BlockPos pos,
                                                BlockPos neighborPos)
    {
        if (state.get(WATERLOGGED))
        {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
