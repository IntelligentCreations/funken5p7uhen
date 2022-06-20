package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
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
        return null;
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
        tooltip.add(new TranslatableText("tooltip.funken5p7uhen.researching_table"));
    }
}
