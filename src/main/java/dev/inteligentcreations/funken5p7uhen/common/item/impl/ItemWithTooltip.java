package dev.inteligentcreations.funken5p7uhen.common.item.impl;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemWithTooltip extends Item {
    private final List<Text> textList;

    public ItemWithTooltip(Settings settings, List<Text> textList) {
        super(settings);
        this.textList = textList;
    }

    public List<Text> getTextList() {
        return textList;
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.addAll(textList);
    }
}
