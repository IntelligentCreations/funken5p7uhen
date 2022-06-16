package dev.inteligentcreations.funken5p7uhen.common.item.impl;

import dev.inteligentcreations.funken5p7uhen.common.util.blueprint.type.BlueprintType;
import net.minecraft.item.Item;

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
}
