package ru.catunderglue.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.catunderglue.recipesapp.model.Ingredient;
import ru.catunderglue.recipesapp.services.IngredientService;

import java.util.Collection;

@RestController
@RequestMapping("ingredient")
@Tag(name = "Ингредиенты", description = "CRUD-методы для работы с ингредиентами")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Создание ингредиента"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Новый ингредиент в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class)
                    )
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент создан",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class)
                    )
            }
    )
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.createIngredient(ingredient));
    }

    @GetMapping()
    @Operation(
            summary = "Получение всех ингредиентов."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиенты найдены",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    public ResponseEntity<Collection<Ingredient>> getAllIngredient() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("{ingredientID}")
    @Operation(
            summary = "Получение ингредиента по ID"
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент найден",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Ingredient.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден",
                            content = @Content()
                    )
            }
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int ingredientID) {
        Ingredient ingredient = ingredientService.getIngredientByID(ingredientID);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("{ingredientID}")
    @Operation(
            summary = "Изменение ингредиента по ID."
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "0"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Изменённый ингредиент в формате JSON",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Ingredient.class)
                    )
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент изменён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Ingredient.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден",
                            content = @Content()
                    )
            }
    )
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient, @PathVariable int ingredientID) {
        Ingredient updatedIngredient = ingredientService.updateIngredientByID(ingredientID, ingredient);
        if (updatedIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("{ingredientID}")
    @Operation(
            summary = "Удаление ингредиента по ID."
    )
    @Parameter(
            name = "ingredientID",
            description = "ID необходимого ингредиента",
            example = "0"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент успешно удалён",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Ingredient.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент не найден",
                            content = @Content()
                    )
            }
    )
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable int ingredientID) {
        Ingredient deletedIngredient = ingredientService.deleteIngredientByID(ingredientID);
        if (deletedIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedIngredient);
    }
}
