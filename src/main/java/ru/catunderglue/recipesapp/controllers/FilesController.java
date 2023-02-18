package ru.catunderglue.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.catunderglue.recipesapp.services.FilesService;
import ru.catunderglue.recipesapp.services.RecipeService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping(path = "files")
@Tag(name = "Файлы", description = "Методы для работы с файлами")
public class FilesController {

    private final FilesService filesService;
    private final RecipeService recipeService;

    public FilesController(FilesService filesService, RecipeService recipeService) {
        this.filesService = filesService;
        this.recipeService = recipeService;
    }

    @GetMapping(value = "export/recipes")
    @Operation(
            summary = "Экспорт рецептов в файле json"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты успешно выгружены"
    )

    public ResponseEntity<InputStreamResource> exportRecipeDataFile() throws FileNotFoundException {
        File file = filesService.getRecipeDataFileInfo();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(
            summary = "Импорт рецептов файлом json"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты успешно загружены"
    )
    @PostMapping(value = "import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importRecipeDataFile(@RequestParam MultipartFile file) {
        try {
            filesService.importRecipeDataFile(file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @Operation(
            summary = "Импорт ингредиентов файлом json"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиенты успешно загружены"
    )
    @PostMapping(value = "import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importIngredientDataFile(@RequestParam MultipartFile file) {
        try {
            filesService.importIngredientDataFile(file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("export")
    @Operation(
            summary = "Экспорт рецептов в файл в формате txt"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Успешный экспорт рецептов",
            content = {
                    @Content(
                            mediaType = "text/plain"
                    )
            }
    )
    public ResponseEntity<Object> getRecipeTextFile() {
        try {
            Path path = recipeService.createRecipeTextFile();
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }

    }
}
