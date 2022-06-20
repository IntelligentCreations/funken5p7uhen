package dev.inteligentcreations.funken5p7uhen.common.item.impl;

import dev.inteligentcreations.funken5p7uhen.common.util.blueprint.type.BlueprintType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlueprintItem extends Item
{
    private final BlueprintType type;

    public BlueprintItem(BlueprintType type,
                         Settings settings)
    {
        super(settings);
        this.type = type;
    }

    public BlueprintType getType()
    {
        return this.type;
    }

    public void appendTooltip(ItemStack stack,
                              @Nullable World world,
                              List<Text> tooltip,
                              TooltipContext context)
    {
        tooltip.add(new TranslatableText("tooltip.funken5p7uhen.blueprint").formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(new TranslatableText("tooltip.funken5p7uhen.blueprint.type", I18n.translate("blueprintType.funken5p7uhen." + getType().getIdentifier().getPath())).formatted(Formatting.BLUE));
    }
}
