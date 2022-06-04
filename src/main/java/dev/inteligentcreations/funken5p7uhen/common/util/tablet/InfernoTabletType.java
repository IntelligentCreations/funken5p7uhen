package dev.inteligentcreations.funken5p7uhen.common.util.tablet;

import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.util.Identifier;

public enum InfernoTabletType implements IInfernoTabletType
{
    MARCH(funken5p7uhen.MOD_ID, "march"),
    ANNIHILATION(funken5p7uhen.MOD_ID, "annihilation"),
    ATTRACTION(funken5p7uhen.MOD_ID, "attraction"),
    INFILTRATION(funken5p7uhen.MOD_ID, "infiltration");

    private final Identifier identifier;
    private final String namespace;
    private final String name;

    InfernoTabletType(String namespace,
                      String name)
    {
        this.identifier = new Identifier(namespace, name);
        this.namespace = namespace;
        this.name = name;
    }

    @Override
    public Identifier getIdentifier()
    {
        return this.identifier;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getNamespace()
    {
        return namespace;
    }
}
