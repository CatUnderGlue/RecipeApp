package ru.catunderglue.recipesapp.services;

public interface FilesService {
    /**
     * Сохранение рецептов в файл
     *
     * @param json строковое представление рецептов в формате json
     * @return true - при успешном сохранении, false - при неудачи
     */
    boolean saveRecipesToFile(String json);

    /**
     * Считывание рецептов из файла
     *
     * @return строковое представление рецептов
     */
    String readRecipesFromFile();

    /**
     * @param json строковое представление ингредиентов в формате json
     * @return true - при успешном сохранении, false - при неудачи
     */
    boolean saveIngredientsToFile(String json);

    /**
     * Считывание ингредиентов из файла
     *
     * @return строковое представление ингредиентов
     */
    String readIngredientsFromFile();

}
