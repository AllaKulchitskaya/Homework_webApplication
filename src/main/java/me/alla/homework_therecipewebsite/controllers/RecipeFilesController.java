package me.alla.homework_therecipewebsite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.alla.homework_therecipewebsite.services.RecipeFilesService;
import me.alla.homework_therecipewebsite.services.RecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Tag(name = "Файлы с рецептами", description = "CRUD-операции для работы с файлами")
@RequestMapping("/recipesFiles")
public class RecipeFilesController {

    private final RecipeFilesService recipeFilesService;

    private final RecipeService recipeService;

    public RecipeFilesController(RecipeFilesService recipeFilesService, RecipeService recipeService) {
        this.recipeFilesService = recipeFilesService;
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Скачать файл с рецептами в формате .json")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно скачан"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка в запросе"
            )
    })
    public ResponseEntity<InputStreamResource> downloadRecipesFile() throws FileNotFoundException {
        File file = recipeFilesService.getRecipesFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл с рецептами")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно загружен"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя проблема сервера"
            )
    })
    public ResponseEntity<Void> uploadRecipesFile (@RequestParam MultipartFile file) {
        recipeFilesService.cleanFile();
        File dataFile = recipeFilesService.getRecipesFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/download")
    @Operation(summary = "Скачать файл с рецептами в формате .txt")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно скачан"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка в запросе"
            )
    })
    public ResponseEntity<byte[]> downloadRecipesText() {
        byte[] file = recipeService.downloadRecipesText();
        if (file == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(file.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.txt\"")
                .body(file);
    }
}
