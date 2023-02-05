package ru.catunderglue.recipesapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.IngredientService;
import ru.catunderglue.recipesapp.services.RecipeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResipeServiceImpl implements RecipeService {

    private static int idGenerator = 0;
    private static final Map<Integer, Recipe> recipeMap = new HashMap<>();

    @Autowired
    private IngredientService ingredientService;

    @Override
    public int getSize() {
        return recipeMap.size();
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        recipe.setId(idGenerator);
        for (Ingredient ingredient : recipe.getIngredients()){
            ingredientService.createIngredient(ingredient);
        }
        recipeMap.put(idGenerator++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipeByID(int id) {
        return recipeMap.getOrDefault(id, null);

    }

    @Override
    public Recipe updateRecipeByID(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)){
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe deleteRecipeByID(int id) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.remove(id);
        } else {
            return null;
        }
    }
}
