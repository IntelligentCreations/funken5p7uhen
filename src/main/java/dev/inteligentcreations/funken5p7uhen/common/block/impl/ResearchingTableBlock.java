package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.ResearchingTableBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ResearchingTableBlock extends BlockWithEntity
{
    public ResearchingTableBlock(Settings arg)
    {
        super(arg);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos,
                                         BlockState state)
    {
        return new ResearchingTableBlockEntity(pos, state);
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
        tooltip.add(new TranslatableText("tooltip.funken5p7uhen.researching_table")
                .formatted(Formatting.GRAY, Formatting.ITALIC));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state,
                                      BlockView view,
                                      BlockPos pos,
                                      ShapeContext context)
    {
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.0625, 0, 0.0625,
                        0.9375, 0.125, 0.9375),
                VoxelShapes.cuboid(0.1875, 0.125, 0.1875,
                        0.8125, 0.875, 0.8125),
                VoxelShapes.cuboid(0.125, 0.875, 0.125,
                        0.875, 0.9375, 0.875),
                VoxelShapes.cuboid(0, 0.9375, 0,
                        1, 1, 1)
        );
    }
}
