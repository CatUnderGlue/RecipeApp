package ru.catunderglue.recipesapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.FilesService;
import ru.catunderglue.recipesapp.services.IngredientService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static int idGenerator = 0;
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private final FilesService filesService;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        ingredientMap.put(idGenerator++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient getIngredientByID(int id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient updateIngredientByID(int id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient deleteIngredientByID(int id) {
        if (ingredientMap.containsKey(id)) {
            Ingredient removedIngredient = ingredientMap.remove(id);
            saveToFile();
            return removedIngredient;
        } else {
            return null;
        }
    }

    @Override
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredientMap;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveIngredientsToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesService.readIngredientsFromFile();
        try {
            if (!json.isBlank()) {
                ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
                });
                idGenerator = ingredientMap.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
