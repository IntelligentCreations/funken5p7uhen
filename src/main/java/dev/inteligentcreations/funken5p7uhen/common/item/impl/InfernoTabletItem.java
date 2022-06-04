package dev.inteligentcreations.funken5p7uhen.common.item.impl;

import dev.inteligentcreations.funken5p7uhen.common.util.tablet.IInfernoTabletType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class InfernoTabletItem extends Item
{
    private final IInfernoTabletType type;

    public InfernoTabletItem(IInfernoTabletType type,
                             Settings settings)
    {
        super(settings);
        this.type = type;
    }

    public IInfernoTabletType getType()
    {
        return this.type;
    }

    @Override
    public void appendTooltip(ItemStack itemStack,
                              World world,
                              List<Text> tooltip,
                              TooltipContext tooltipContext)
    {
        tooltip.add(new TranslatableText("item.funken5p7uhen.inferno_tablet.tooltip")
                .formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(new TranslatableText("item.funken5p7uhen.inferno_tablet.type_info",
                I18n.translate(type.getTranslatableKey()))
                .formatted(Formatting.GOLD));
    }
}
