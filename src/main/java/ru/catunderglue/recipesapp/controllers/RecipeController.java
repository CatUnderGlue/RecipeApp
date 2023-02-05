package ru.catunderglue.recipesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.RecipeService;

@RestController
@RequestMapping("recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        recipeService.createRecipe(recipe);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("some")
    public ResponseEntity<String> createRecipes(@RequestBody Recipe[] recipes) {
        for (Recipe recipe : recipes) {
            recipeService.createRecipe(recipe);
        }
        return ResponseEntity.ok("Success");
    }

    @GetMapping("{recipeID}")
    public String getRecipe(@PathVariable int recipeID) {
        Recipe recipe = recipeService.getRecipeByID(recipeID);
        if (recipe == null) {
            return ResponseEntity.notFound().build().toString();
        }
        return recipe.toString();
    }

    @GetMapping("all")
    public String getAllRecipes() {
        if (recipeService.getSize() == 0) {
            return ResponseEntity.notFound().build().toString();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipeService.getSize(); i++) {
            builder.append(recipeService.getRecipeByID(i)).append("<br><br>");
        }
        return builder.toString();
    }

    @PutMapping("{recipeID}")
    public ResponseEntity<Integer> updateRecipe(@RequestBody Recipe recipe, @PathVariable int recipeID) {
        Recipe updatedRecipe = recipeService.updateRecipeByID(recipeID, recipe);
        if (updatedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRecipe.getId());
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
