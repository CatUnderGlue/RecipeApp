package ru.catunderglue.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Recipe;
import ru.catunderglue.recipesapp.services.RecipeService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("recipe")
@Tag(name = "Рецепты", description = "CRUD-методы для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(
            summary = "Создание рецепта."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Новый рецепт в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class)
                    )
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепт создан",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class)
                    )
            }
    )
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.createRecipe(recipe));
    }

    @PostMapping("some")
    @Operation(
            summary = "Создание нескольких рецептов за раз."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Список с новыми рецептами в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты созданы",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            }
    )
    public ResponseEntity<List<Recipe>> createRecipes(@RequestBody List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            recipeService.createRecipe(recipe);
        }
        return ResponseEntity.ok(recipes);
    }


    @GetMapping()
    @Operation(
            summary = "Получение всех рецептов с возможностью фильтрации.",
            description = "Фильтрует рецепты по указанному id ингредиента или выдаёт все рецепты, если id не был указан"
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "1"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты найдены",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            }
    )
    public ResponseEntity<Collection<Recipe>> getAllRecipes(@RequestParam(required = false, defaultValue = "-1") Integer ingredientID) {
        Collection<Recipe> recipes = recipeService.getRecByIngredId(ingredientID);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("pagination")
    @Operation(
            summary = "Получение рецептов по страницам.",
            description = "Выдаёт рецепты, разбитые на страницы по нужному количеству"
    )
    @Parameters(value = {
            @Parameter(
                    name = "page",
                    description = "Номер страницы",
                    example = "1"),
            @Parameter(
                    name = "limit",
                    description = "Максимальное кол-во рецептов на странице",
                    example = "5")
    }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты найдены",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            }
    )
    public ResponseEntity<Collection<Recipe>> getRecipesPerPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        Collection<Recipe> recipes = recipeService.pagination(page, limit);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("{recipeID}")
    @Operation(
            summary = "Получение рецепта по ID."
    )
    @Parameter(
            name = "recipeID",
            description = "ID необходимого рецепта",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Recipe.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден",
                            content = @Content(
                                    mediaType = "text"
                            )
                    )
            }
    )
    public ResponseEntity<Recipe> getRecipe(@PathVariable int recipeID) {
        Recipe recipe = recipeService.getRecipeByID(recipeID);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("{recipeID}")
    @Operation(
            summary = "Изменение рецепта по ID."
    )
    @Parameter(
            name = "recipeID",
            description = "ID необходимого рецепта",
            example = "0"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Изменённый рецепт в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class)
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт изменён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Recipe.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден",
                            content = @Content(
                                    mediaType = "text"
                            )
                    )
            }
    )
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe, @PathVariable int recipeID) {
        Recipe updatedRecipe = recipeService.updateRecipeByID(recipeID, recipe);
        if (updatedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("{recipeID}")
    @Operation(
            summary = "Удаление рецепта по ID."
    )
    @Parameter(
            name = "recipeID",
            description = "ID необходимого рецепта",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Рецепт успешно удалён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Recipe.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Рецепт не найден",
                            content = @Content(
                                    mediaType = "text"
                            )
                    )
            }
    )
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int recipeID) {
        Recipe deletedRecipe = recipeService.deleteRecipeByID(recipeID);
        if (deletedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedRecipe);
    }
}
