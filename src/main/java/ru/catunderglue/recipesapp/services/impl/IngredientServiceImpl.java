package ru.catunderglue.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.IngredientService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static int idGenerator = 0;
    private static final Map<Integer, Ingredient> INGREDIENT_MAP = new HashMap<>();

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        INGREDIENT_MAP.put(idGenerator++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredientByID(int id) {
        return INGREDIENT_MAP.get(id);
    }

    @Override
    public Ingredient updateIngredientByID(int id, Ingredient ingredient) {
        if (INGREDIENT_MAP.containsKey(id)) {
            INGREDIENT_MAP.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient deleteIngredientByID(int id) {
        if (INGREDIENT_MAP.containsKey(id)) {
            return INGREDIENT_MAP.remove(id);
        } else {
            return null;
        }
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return INGREDIENT_MAP.values();
    }
}
