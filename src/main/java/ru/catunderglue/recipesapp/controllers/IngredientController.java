package ru.catunderglue.recipesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.IngredientService;

@RestController
@RequestMapping("ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<Integer> createIngredient(@RequestBody Ingredient ingredient) {
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return ResponseEntity.ok(createdIngredient.getId());
    }

    @GetMapping("{ingredientID}")
    public String getIngredient(@PathVariable int ingredientID){
        Ingredient ingredient = ingredientService.getIngredientByID(ingredientID);
        if (ingredient == null){
            return ResponseEntity.notFound().build().toString();
        }
        return ingredient.toString();
    }

    @GetMapping("all")
    public String getAllIngredient(){
        if (ingredientService.getSize() == 0){
            return ResponseEntity.notFound().build().toString();
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ingredientService.getSize(); i++) {
            Ingredient ingredient = ingredientService.getIngredientByID(i);
            builder.append("Id: ").append(ingredient.getId()).append(" ").append(ingredient).append("<br><br>");
        }
        return builder.toString();
    }

    @PutMapping("{ingredientID}")
    public ResponseEntity<Integer> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable int ingredientID){
        Ingredient updatedIngredient = ingredientService.updateIngredientByID(ingredientID, ingredient);
        if (updatedIngredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedIngredient.getId());
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
