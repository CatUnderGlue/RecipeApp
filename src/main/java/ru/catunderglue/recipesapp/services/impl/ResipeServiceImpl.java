package ru.catunderglue.recipesapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.FilesService;
import ru.catunderglue.recipesapp.services.IngredientService;
import ru.catunderglue.recipesapp.services.RecipeService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ResipeServiceImpl implements RecipeService {
    private static int idGenerator = 0;
    private static Map<Integer, Recipe> recipeMap = new HashMap<>();

    private final IngredientService ingredientService;
    private final FilesService filesService;

    public ResipeServiceImpl(IngredientService ingredientService, FilesService filesService) {
        this.ingredientService = ingredientService;
        this.filesService = filesService;
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredientService.createIngredient(ingredient);
        }
        recipeMap.put(idGenerator++, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe getRecipeByID(int id) {
        return recipeMap.get(id);
    }

    @Override
    public Collection<Recipe> getRecByIngredId(Integer id) {
        Collection<Recipe> recipes = new ArrayList<>();
        Ingredient ingredientTmp = ingredientService.getIngredientByID(id);
        for (Recipe recipe : recipeMap.values()) {
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
    public Map<Integer, Recipe> pagination(Integer page, Integer limit) {
        Map<Integer, Recipe> recipes = new HashMap<>();
        if (page == 0 && limit == 0) {
            return getAllRecipes();
        } else if (page == 0) {
            page = 1;
        } else if (limit == 0) {
            limit = 10;
        }
        for (int i = page * limit - limit; i < page * limit; i++) {
            if (recipeMap.containsKey(i)) {
                recipes.put(i, recipeMap.get(i));
            }
        }
        return recipes;
    }

    @Override
    public Recipe updateRecipeByID(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public Recipe deleteRecipeByID(int id) {
        if (recipeMap.containsKey(id)) {
            Recipe removedRecipe = recipeMap.remove(id);
            saveToFile();
            return removedRecipe;
        } else {
            return null;
        }
    }

    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return recipeMap;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveRecipesToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesService.readRecipesFromFile();
        try {
            if (!json.isBlank()) {
                recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
                });
                idGenerator = recipeMap.size(); // Как же долго я не мог понять, почему рецепты исчезают
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
