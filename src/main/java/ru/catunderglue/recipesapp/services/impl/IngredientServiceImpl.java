package ru.catunderglue.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.IngredientService;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final Map<Integer, Ingredient> ingredientsMap = new TreeMap<>();

    @Override
    public int getSize(){
        return ingredientsMap.size();
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredientsMap.put(ingredient.getId(), ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientByID(int id) {
        return ingredientsMap.getOrDefault(id, null);
    }

    @Override
    public Ingredient updateIngredientByID(int id, Ingredient ingredient) {
        ingredientsMap.put(id, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient deleteIngredientByID(int id) {
        return ingredientsMap.remove(id);
    }
}
