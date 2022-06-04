package dev.inteligentcreations.funken5p7uhen.common.block.impl;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;

import java.util.List;

public class EmberIntersectionBlock extends Block
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
}
