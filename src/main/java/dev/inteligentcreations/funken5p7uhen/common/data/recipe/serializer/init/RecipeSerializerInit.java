package dev.inteligentcreations.funken5p7uhen.common.data.recipe.serializer.init;

import dev.inteligentcreations.funken5p7uhen.common.data.recipe.serializer.impl.DevelopingStationRecipeSerializer;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl.DevelopingStationRecipe;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RecipeSerializerInit
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registry.RECIPE_SERIALIZER_KEY, funken5p7uhen.MOD_ID);

    public static final RegistryObject<RecipeSerializer<DevelopingStationRecipe>> DEVELOPING = SERIALIZERS.register(DevelopingStationRecipeSerializer.ID, () ->
            DevelopingStationRecipeSerializer.INSTANCE);
}
