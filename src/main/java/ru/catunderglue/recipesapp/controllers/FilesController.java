package ru.catunderglue.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.catunderglue.recipesapp.services.FilesService;

import java.io.*;

@RestController
@RequestMapping(path = "files")
public class FilesController {

    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping(value = "export/recipes")
    @Operation(
            summary = "Экспорт рецептов в файле"
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
            summary = "Импорт рецептов файлом"
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
            summary = "Импорт ингредиентов файлом"
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
}
