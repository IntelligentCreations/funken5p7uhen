package dev.inteligentcreations.funken5p7uhen.common.util.blueprint.type;

import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;

public enum BlueprintTypeImpl
        implements BlueprintType, StringIdentifiable
{
    EMBER_TUNNEL(new Identifier("funken5p7uhen", "ember_tunnel")),
    EMBER_CONTAINER(new Identifier("funken5p7uhen", "ember_container")),
    EMBER_INTERSECTION(new Identifier("funken5p7uhen", "ember_intersection")),
    EMBER_PUMP(new Identifier("funken5p7uhen", "ember_pump"));

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

    @Override
    public String asString()
    {
        return getIdentifier().getPath();
    }
}
