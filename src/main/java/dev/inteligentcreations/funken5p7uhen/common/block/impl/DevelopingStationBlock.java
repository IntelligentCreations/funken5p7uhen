package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.DevelopingStationBlockEntity;
// import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
// import net.minecraft.block.entity.BlockEntityTicker;
// import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DevelopingStationBlock {} /* extends BlockWithEntity
{
    public DevelopingStationBlock(Settings arg)
    {
        super(arg);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    public BlockState rotate(BlockState state,
                             BlockRotation rotation)
    {
        return state.with(Properties.HORIZONTAL_FACING, rotation.rotate(state.get(Properties.HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState state,
                             BlockMirror mirror)
    {
        return state.rotate(mirror.getRotation(state.get(Properties.HORIZONTAL_FACING)));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos,
                                         BlockState state)
    {
        return new DevelopingStationBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public void appendTooltip(ItemStack stack,
                              @Nullable BlockView world,
                              List<Text> tooltip,
                              TooltipContext options)
    {
        tooltip.add(new TranslatableText("tooltip.funken5p7uhen.developing_station")
                .formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(new LiteralText("WIP")
                .formatted(Formatting.RED, Formatting.BOLD));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,
                                      BlockView view,
                                      BlockPos pos,
                                      ShapeContext context)
    {
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.0625, 0, 0.0625,
                        0.9375, 0.0625, 0.9375),
                VoxelShapes.cuboid(0.0625, 0.46663125, 0.0625,
                        0.9375, 0.71663125, 0.9375)
        );
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING,
                ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state,
                              World world,
                              BlockPos pos,
                              PlayerEntity player,
                              Hand hand,
                              BlockHitResult hit)
    {
        if (!world.isClient)
        {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null)
            {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state,
                                World world,
                                BlockPos pos,
                                BlockState newState,
                                boolean moved)
    {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DevelopingStationBlockEntity be)
            {
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), be.getStack(0));
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), be.getStack(1));
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type)
    {
        return checkType(type, BlockEntityInit.DEVELOPING_STATION.get(), DevelopingStationBlockEntity::tick);
    }
}
*/
