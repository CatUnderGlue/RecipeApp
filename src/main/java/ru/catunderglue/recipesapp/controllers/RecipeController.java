package ru.catunderglue.recipesapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.RecipeService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.createRecipe(recipe));
    }

    @PostMapping("some")
    public ResponseEntity<List<Recipe>> createRecipes(@RequestBody List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            recipeService.createRecipe(recipe);
        }
        return ResponseEntity.ok(recipes);
    }

    @GetMapping()
    public ResponseEntity<Collection<Recipe>> getAllRecipes(@RequestParam(required = false, defaultValue = "-1") Integer ingredientID) {
        Collection<Recipe> recipes = recipeService.getRecByIngredId(ingredientID);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("{recipeID}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int recipeID) {
        Recipe recipe = recipeService.getRecipeByID(recipeID);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("{recipeID}")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe, @PathVariable int recipeID) {
        Recipe updatedRecipe = recipeService.updateRecipeByID(recipeID, recipe);
        if (updatedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("{recipeID}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int recipeID) {
        Recipe deletedRecipe = recipeService.deleteRecipeByID(recipeID);
        if (deletedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedRecipe);
    }
}
