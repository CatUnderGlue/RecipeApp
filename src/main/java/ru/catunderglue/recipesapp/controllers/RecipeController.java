package ru.catunderglue.recipesapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.RecipeService;

@RestController
@RequestMapping("recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
        // Тестовый рецепт
        String[] steps = new String[]{
                "Смешайте весь творог с яйцами, сахаром и тщательно всё перемешайте.",
                "Всыпьте в творог муку и тщательно перемешайте.",
                "Поставьте сковороду на средний огонь и налейте в нее подсолнечное масло.",
                "Слепите несколько небольших шариков из получившейся творожной массы и положите их на тарелку. Затем по очереди обкатывайте творожные шарики в муке и выкладывайте на сковороду.",
                "Обжаривайте сырники 1–2 минуты до появления золотистой корочки. Затем переверните их на другую сторону и также обжарьте до золотистого состояния.",
                "Повторяйте, пока творог не закончится."};
        Ingredient[] ingredients = new Ingredient[]{
                new Ingredient("Творог", 350, "г."),
                new Ingredient("Куриное яйцо", 2, "шт."),
                new Ingredient("Пшеничная мука", 6, "ст.л."),
                new Ingredient("Сахар", 2, "ст.л.")};
        this.recipeService.createRecipe(new Recipe("Сырники из творога", 30, 2, ingredients, steps));
    }

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
