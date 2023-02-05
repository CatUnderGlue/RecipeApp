package ru.catunderglue.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.IngredientService;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static int idGenerator = 0;
    private static final Map<Integer, Ingredient> ingredientsMap = new HashMap<>();

    @Override
    public int getSize() {
        return ingredientsMap.size();
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredient.setId(idGenerator);
        ingredientsMap.put(idGenerator++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientByID(int id) {
        return ingredientsMap.getOrDefault(id, null);
    }

    @Override
    public Ingredient updateIngredientByID(int id, Ingredient ingredient) {
        if (ingredientsMap.containsKey(id)) {
            ingredientsMap.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient deleteIngredientByID(int id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.remove(id);
        } else {
            return null;
        }
    }
}
