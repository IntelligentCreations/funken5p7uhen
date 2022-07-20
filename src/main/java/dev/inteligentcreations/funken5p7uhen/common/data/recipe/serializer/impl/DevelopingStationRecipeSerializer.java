package dev.inteligentcreations.funken5p7uhen.common.data.recipe.serializer.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl.DevelopingStationRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class DevelopingStationRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>>
        implements RecipeSerializer<DevelopingStationRecipe>
{
    public static final DevelopingStationRecipeSerializer INSTANCE = new DevelopingStationRecipeSerializer();

    public static final String ID = "developing";

    @Override
    public DevelopingStationRecipe read(Identifier id,
                                        JsonObject json)
    {
        DevelopingStationRecipe.RecipeJsonFormat recipeJson = new Gson().fromJson(json,
                DevelopingStationRecipe.RecipeJsonFormat.class);
        if (recipeJson.getInput() == null || recipeJson.getOutputItem() == null)
            throw new JsonSyntaxException("A required attribute is missing.");
        Ingredient input = Ingredient.fromJson(recipeJson.getInput());
        Item output = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.getOutputItem()))
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.getOutputItem()));
        ItemStack outputStack = new ItemStack(output, 1);
        return new DevelopingStationRecipe(id, outputStack, input);
    }

    @Nullable
    @Override
    public DevelopingStationRecipe read(Identifier id,
                                        PacketByteBuf buf)
    {
        Ingredient input = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();
        return new DevelopingStationRecipe(id, output, input);
    }

    @Override
    public void write(PacketByteBuf buf,
                      DevelopingStationRecipe recipe)
    {
        recipe.getInput().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }
}
