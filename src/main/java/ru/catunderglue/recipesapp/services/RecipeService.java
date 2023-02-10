package ru.catunderglue.recipesapp.services;

import ru.catunderglue.recipesapp.model.Recipe;

import java.util.Collection;
import java.util.Map;

public interface RecipeService {
    /**
     * Создание рецепта
     *
     * @param recipe новый рецепт
     * @return созданный рецепт
     */
    Recipe createRecipe(Recipe recipe);

    /**
     * Получение рецепта по id
     *
     * @param id идентификатор рецепта
     * @return искомый рецепт
     */
    Recipe getRecipeByID(int id);

    /**
     * Получение рецептов по id ингредиента
     *
     * @param id идентификатор рецепта
     * @return отфильтрованные рецепты
     */
    Collection<Recipe> getRecByIngredId(Integer id);

    /**
     * Вывод рецептов постранично
     *
     * @param page  номер страницы
     * @param limit кол-во рецептов на страницу
     * @return карта с рецептами с указанной страницы и с указанным лимитом
     */
    Map<Integer, Recipe> pagination(Integer page, Integer limit);

    /**
     * Изменение рецепта по id
     *
     * @param id     идентификатор изменяемого рецепта
     * @param recipe новый рецепт
     * @return изменённый рецепт
     */

    Recipe updateRecipeByID(int id, Recipe recipe);

    /**
     * Удаление рецепта по id
     *
     * @param id идентификатор рецепта
     * @return удалённый рецепт
     */
    Recipe deleteRecipeByID(int id);

    /**
     * Получение всех рецептов
     *
     * @return карта со всеми рецептами
     */
    Map<Integer, Recipe> getAllRecipes();
}
