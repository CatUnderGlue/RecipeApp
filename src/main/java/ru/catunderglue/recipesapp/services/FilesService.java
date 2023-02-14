package ru.catunderglue.recipesapp.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FilesService {
    /**
     * Сохранение рецептов в файл
     *
     * @param json строковое представление рецептов в формате json
     * @return true - при успешном сохранении, false - при неудаче
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
     * @return true - при успешном сохранении, false - при неудаче
     */
    boolean saveIngredientsToFile(String json);

    /**
     * Считывание ингредиентов из файла
     *
     * @return строковое представление ингредиентов
     */
    String readIngredientsFromFile();

    /**
     * Загрузка файла с рецептами от пользователя
     *
     * @param file файл с рецептами в формате json
     */
    void importRecipeDataFile(MultipartFile file) throws IOException;

    /**
     * Загрузка файла с ингредиентами от пользователя
     *
     * @param file файл с ингредиентами в формате json
     */
    void importIngredientDataFile(MultipartFile file) throws IOException;

    /**
     * Получение информации по файлу с рецептами
     *
     * @return инофрмация о файле
     */
    File getRecipeDataFileInfo();

    /**
     * Получение информации по файлу с ингредиентами
     *
     * @return инофрмация о файле
     */
    File getIngredientDataFileInfo();
}
