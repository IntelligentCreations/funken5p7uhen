package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberContainerBlockEntity;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class EmberContainerBlock extends BlockWithEntity
{
    public EmberContainerBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack,
                              BlockView world,
                              List<Text> tooltip,
                              TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("block.funken5p7uhen.ember_container.tooltip")
                .formatted(Formatting.GRAY, Formatting.ITALIC));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,
                                      BlockView view,
                                      BlockPos pos,
                                      ShapeContext ctx)
    {
        return VoxelShapes.union(VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f),
                VoxelShapes.cuboid(0.0f, 0.9375f, 0.0f, 1.0f, 1.0f, 1.0f),
                VoxelShapes.cuboid(0.0625f, 0.0625f, 0.0625f, 0.9375f, 0.9375f,0.9375f));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos,
                                         BlockState state)
    {
        return new EmberContainerBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world,
                                                                  BlockState state,
                                                                  BlockEntityType<T> type)
    {
        return checkType(type, BlockEntityInit.EMBER_CONTAINER.get(), EmberContainerBlockEntity::tick);
    }
}
