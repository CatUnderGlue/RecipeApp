package ru.catunderglue.recipesapp.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.services.FilesService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value(value = "${path.to.data.files}")
    private String dataFilesPath;

    @Value(value = "${name.of.recipe.data.file}")
    private String recipeDataFileName;

    @Value(value = "${name.of.ingredient.data.file}")
    private String ingredientDataFileName;

    @Override
    public boolean saveRecipesToFile(String json) {
        return saveToFile(json, recipeDataFileName);
    }

    @Override
    public String readRecipesFromFile() {
        return readFromFile(recipeDataFileName);
    }

    @Override
    public boolean saveIngredientsToFile(String json) {
        return saveToFile(json, ingredientDataFileName);
    }

    @Override
    public String readIngredientsFromFile() {
        return readFromFile(ingredientDataFileName);
    }

    public boolean cleanDataFile(String dataFileName) {
        Path path = Path.of(dataFilesPath, dataFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readFromFile(String dataFileName) {
        try {
            return Files.readString(Path.of(dataFilesPath, dataFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean saveToFile(String json, String dataFileName) {
        try {
            cleanDataFile(dataFileName);
            Files.writeString(Path.of(dataFilesPath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Оказывается, если файла нет, то инициализировать данные неоткуда :D
    @PostConstruct
    private void init() {
        try {
            if (Files.notExists(Path.of(dataFilesPath, recipeDataFileName))) {
                Files.createFile(Path.of(dataFilesPath, recipeDataFileName));
            }
            if (Files.notExists(Path.of(dataFilesPath, ingredientDataFileName))) {
                Files.createFile(Path.of(dataFilesPath, ingredientDataFileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
