package ru.catunderglue.recipesapp.services;

import ru.catunderglue.recipesapp.model.Recipe;

import java.util.Collection;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe);

    Recipe getRecipeByID(int id);

    Recipe updateRecipeByID(int id, Recipe recipe);

    Recipe deleteRecipeByID(int id);

    Collection<Recipe> getAllRecipes();
}
