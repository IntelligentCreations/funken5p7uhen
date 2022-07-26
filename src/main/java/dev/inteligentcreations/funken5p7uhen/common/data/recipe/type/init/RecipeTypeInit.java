package dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.init;

import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl.DevelopingStationRecipe;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RecipeTypeInit
{
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_KEY, funken5p7uhen.MOD_ID);

    // public static final RegistryObject<RecipeType<DevelopingStationRecipe>> DEVELOPING = TYPES.register(DevelopingStationRecipe.Type.ID, () ->
    //         DevelopingStationRecipe.Type.INSTANCE);
}
