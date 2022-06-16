package dev.inteligentcreations.funken5p7uhen.common.util.blueprint.type;

import net.minecraft.util.Identifier;

public interface BlueprintType
{
    Identifier getIdentifier();

    default String getTranslatableKey()
    {
        return "blueprint." + this.getIdentifier().getNamespace() + "." + this.getIdentifier().getPath();
    }
}
