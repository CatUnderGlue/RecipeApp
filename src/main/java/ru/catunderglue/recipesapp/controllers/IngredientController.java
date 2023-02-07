package ru.catunderglue.recipesapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.IngredientService;

import java.util.Collection;

@RestController
@RequestMapping("ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("{ingredientID}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int ingredientID){
        Ingredient ingredient = ingredientService.getIngredientByID(ingredientID);
        if (ingredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("all")
    public ResponseEntity<Collection<Ingredient>> getAllIngredient(){
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PutMapping("{ingredientID}")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable int ingredientID){
        Ingredient updatedIngredient = ingredientService.updateIngredientByID(ingredientID, ingredient);
        if (updatedIngredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("{ingredientID}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable int ingredientID){
        Ingredient deletedIngredient = ingredientService.deleteIngredientByID(ingredientID);
        if (deletedIngredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedIngredient);
    }
}
