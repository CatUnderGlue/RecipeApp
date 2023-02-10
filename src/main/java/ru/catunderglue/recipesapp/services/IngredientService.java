package ru.catunderglue.recipesapp.services;

import ru.catunderglue.recipesapp.model.Ingredient;

import java.util.Map;

public interface IngredientService {

    /**
     * Создание ингредиента
     *
     * @param ingredient новый ингредиент
     * @return созданный ингредиент
     */
    Ingredient createIngredient(Ingredient ingredient);

    /**
     * Получение ингредиента по id
     *
     * @param id идентификатор ингредиента
     * @return искомый ингредиент
     */
    Ingredient getIngredientByID(int id);

    /**
     * Изменение ингредиента
     *
     * @param id         идентификатор ингредиента
     * @param ingredient новый ингредиент
     * @return изменённый ингредиент
     */
    Ingredient updateIngredientByID(int id, Ingredient ingredient);

    /**
     * Удаление ингредиента
     *
     * @param id идентификатор ингредиента
     * @return удалённый ингредиент
     */
    Ingredient deleteIngredientByID(int id);

    /**
     * Получение всех ингредиентов
     *
     * @return карта со всеми ингредиентами
     */
    Map<Integer, Ingredient> getAllIngredients();
}
