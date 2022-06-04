package dev.inteligentcreations.funken5p7uhen.common.util.tablet;

import net.minecraft.util.Identifier;

public interface IInfernoTabletType
{
    Identifier getIdentifier();

    String getName();

    String getNamespace();

    default String getTranslatableKey()
    {
        return "tabletType." + this.getNamespace() + "." + this.getName();
    }
}
