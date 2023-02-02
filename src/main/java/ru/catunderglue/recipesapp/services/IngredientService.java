package ru.catunderglue.recipesapp.services;

import ru.catunderglue.recipesapp.model.Ingredient;

public interface IngredientService {
    int getSize();

    Ingredient createIngredient(Ingredient ingredient);

    Ingredient getIngredientByID(int id);

    Ingredient updateIngredientByID(int id, Ingredient ingredient);

    Ingredient deleteIngredientByID(int id);


}
