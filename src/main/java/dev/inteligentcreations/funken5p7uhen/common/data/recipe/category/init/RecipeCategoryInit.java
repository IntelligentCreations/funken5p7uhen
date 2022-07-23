package dev.inteligentcreations.funken5p7uhen.common.data.recipe.category.init;

import net.minecraft.recipe.book.RecipeBookCategory;

public class RecipeCategoryInit
{
    public static RecipeBookCategory DEVELOPING;

    public static void registerCategories()
    {
        DEVELOPING = RecipeBookCategory.create("developing");
    }
}
