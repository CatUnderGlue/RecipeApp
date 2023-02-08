package ru.catunderglue.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.IngredientService;
import ru.catunderglue.recipesapp.services.RecipeService;

import java.util.*;

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
        return RECIPE_MAP.get(id);
    }

    @Override
    public Collection<Recipe> getRecByIngredId(Integer id) {
        if (id == -1){
            return getAllRecipes();
        }
        Collection<Recipe> recipes = new ArrayList<>();
        Ingredient ingredientTmp = ingredientService.getIngredientByID(id);
        for (Recipe recipe : RECIPE_MAP.values()) {
            if (recipe != null) {
                for (Ingredient ingredient : recipe.getIngredients()) {
                    if (ingredient.equals(ingredientTmp) && !recipes.contains(recipe)) {
                        recipes.add(recipe);
                    }
                }
            }
        }
        return recipes;
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
