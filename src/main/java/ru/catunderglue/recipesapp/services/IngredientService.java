package ru.catunderglue.recipesapp.services;

import ru.catunderglue.recipesapp.model.Ingredient;

import java.util.Collection;

public interface IngredientService {

    Ingredient createIngredient(Ingredient ingredient);

    Ingredient getIngredientByID(int id);

    Ingredient updateIngredientByID(int id, Ingredient ingredient);

    Ingredient deleteIngredientByID(int id);


    Collection<Ingredient> getAllIngredients();
}
