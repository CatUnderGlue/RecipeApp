package ru.catunderglue.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.RecipeService;

import java.util.Map;
import java.util.TreeMap;

@Service
public class ResipeServiceImpl implements RecipeService {
    private static final Map<Integer, Recipe> recipeMap = new TreeMap<>();

    @Override
    public int getSize(){
        return recipeMap.size();
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        recipeMap.put(recipe.getId(), recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipeByID(int id) {
        return recipeMap.getOrDefault(id, null);

    }

    @Override
    public Recipe updateRecipeByID(int id, Recipe recipe) {
        recipeMap.put(id, recipe);
        return recipe;
    }

    @Override
    public Recipe deleteRecipeByID(int id) {
        return recipeMap.remove(id);
    }
}
