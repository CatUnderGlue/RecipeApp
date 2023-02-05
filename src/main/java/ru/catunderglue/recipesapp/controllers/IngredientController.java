package ru.catunderglue.recipesapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.IngredientService;

@RestController
@RequestMapping("ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
        this.ingredientService.createIngredient(new Ingredient("Творог", 350, "г."));
        this.ingredientService.createIngredient(new Ingredient("Куриное яйцо", 2, "шт."));
        this.ingredientService.createIngredient(new Ingredient("Пшеничная мука", 6, "ст.л."));
        this.ingredientService.createIngredient(new Ingredient("Сахар", 2, "ст.л."));
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @GetMapping("{ingredientID}")
    public String getIngredient(@PathVariable int ingredientID){
        Ingredient ingredient = ingredientService.getIngredientByID(ingredientID);
        if (ingredient == null){
            return ResponseEntity.notFound().build().toString();
        }
        return ingredient.toString();
    }

    @PutMapping()
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient){
        Ingredient updatedIngredient = ingredientService.updateIngredientByID(ingredient.getId(), ingredient);
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("delete/{ingredientID}")
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable int ingredientID){
        Ingredient deletedIngredient = ingredientService.deleteIngredientByID(ingredientID);
        if (deletedIngredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedIngredient);
    }
}
