package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberContainerBlockEntity;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberTunnelBlockEntity;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmberTunnelBlock extends BlockWithEntity
        implements Waterloggable
{
    public static final BooleanProperty WITH_LAVA = BooleanProperty.of("lava");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = Properties.FACING;

    public EmberTunnelBlock(Settings settings)
    {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WITH_LAVA, false)
                .with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add(FACING);
        stateManager.add(WITH_LAVA);
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
                .with(WITH_LAVA, false)
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    public void appendTooltip(ItemStack itemStack,
                              BlockView world,
                              List<Text> tooltip,
                              TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("block.funken5p7uhen.ember_tunnel.tooltip")
                .formatted(Formatting.GRAY, Formatting.ITALIC));
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

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos,
                                         BlockState state)
    {
        return new EmberTunnelBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type)
    {
        return checkType(type, BlockEntityInit.EMBER_TUNNEL.get(), EmberTunnelBlockEntity::tick);
    }
}
