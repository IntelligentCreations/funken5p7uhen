package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import dev.inteligentcreations.funken5p7uhen.common.blockentity.impl.EmberIntersectionBlockEntity;
import dev.inteligentcreations.funken5p7uhen.common.blockentity.init.BlockEntityInit;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmberIntersectionBlock extends BlockWithEntity
{
    public EmberIntersectionBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack,
                              BlockView world,
                              List<Text> tooltip,
                              TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("block.funken5p7uhen.ember_intersection.tooltip").formatted(Formatting.GRAY, Formatting.ITALIC));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos,
                                         BlockState state)
    {
        return new EmberIntersectionBlockEntity(pos, state);
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
        return checkType(type, BlockEntityInit.EMBER_INTERSECTION.get(), EmberIntersectionBlockEntity::tick);
    }
}
