package dev.inteligentcreations.funken5p7uhen.common.data.recipe.type.impl;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DevelopingStationRecipe implements Recipe<Inventory>
{
    private final Ingredient input;
    private final ItemStack result;
    private final Identifier id;

    public DevelopingStationRecipe(Identifier id,
                                   ItemStack result,
                                   Ingredient input)
    {
        this.id = id;
        this.input = input;
        this.result = result;
    }

    @Override
    public boolean matches(Inventory inventory,
                           World world)
    {
        if(inventory.size() != 3) return false;
        return input.test(inventory.getStack(0));
    }

    public Ingredient getInput()
    {
        return this.input;
    }

    @Override
    public ItemStack craft(Inventory inventory)
    {
        return this.getOutput().copy();
    }

    @Override
    public boolean fits(int width,
                        int height)
    {
        return true;
    }

    @Override
    public ItemStack getOutput()
    {
        return result;
    }

    @Override
    public Identifier getId()
    {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return null;
    }

    @Override
    public RecipeType<?> getType()
    {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DevelopingStationRecipe>
    {
        private Type()
        {
        }
        public static final Type INSTANCE = new Type();
        public static final String ID = "developing";
    }

    public static class RecipeJsonFormat
    {
        private JsonObject input;
        private String outputItem;

        public JsonObject getInput()
        {
            return input;
        }

        public String getOutputItem()
        {
            return outputItem;
        }
    }
}
