package ru.catunderglue.recipesapp.services;

import ru.catunderglue.recipesapp.model.Recipe;

public interface RecipeService {
    int getSize();

    Recipe createRecipe(Recipe recipe);

    Recipe getRecipeByID(int id);

    Recipe updateRecipeByID(int id, Recipe recipe);

    Recipe deleteRecipeByID(int id);
}
