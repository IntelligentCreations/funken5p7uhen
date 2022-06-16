package dev.inteligentcreations.funken5p7uhen.common.util.blueprint.type;

import net.minecraft.util.Identifier;

public enum BlueprintTypeImpl implements BlueprintType
{
    EMBER_TUNNEL(new Identifier("funken5p7uhen", "ember_tunnel")),
    EMBER_CONTAINER(new Identifier("funken5p7uhen", "ember_container")),
    EMBER_INTERSECTION(new Identifier("funken5p7uhen", "ember_intersection"));

    private final Identifier id;

    BlueprintTypeImpl(Identifier id)
    {
        this.id = id;
    }

    @Override
    public Identifier getIdentifier()
    {
        return this.id;
    }
}
