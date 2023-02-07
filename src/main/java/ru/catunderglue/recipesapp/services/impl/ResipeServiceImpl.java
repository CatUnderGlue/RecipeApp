package ru.catunderglue.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.IngredientService;
import ru.catunderglue.recipesapp.services.RecipeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResipeServiceImpl implements RecipeService {
    private static int idGenerator = 0;
    private static final Map<Integer, Recipe> RECIPE_MAP = new HashMap<>();

    private final IngredientService ingredientService;

    public ResipeServiceImpl(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        for (Ingredient ingredient : recipe.getIngredients()){
            ingredientService.createIngredient(ingredient);
        }
        RECIPE_MAP.put(idGenerator++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipeByID(int id) {
        return RECIPE_MAP.getOrDefault(id, null);

    }

    @Override
    public Recipe updateRecipeByID(int id, Recipe recipe) {
        if (RECIPE_MAP.containsKey(id)){
            RECIPE_MAP.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe deleteRecipeByID(int id) {
        if (RECIPE_MAP.containsKey(id)) {
            return RECIPE_MAP.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Collection<Recipe> getAllRecipes() {
        return RECIPE_MAP.values();
    }
}
