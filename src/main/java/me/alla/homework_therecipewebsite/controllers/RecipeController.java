package me.alla.homework_therecipewebsite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.alla.homework_therecipewebsite.model.Recipe;
import me.alla.homework_therecipewebsite.services.RecipeService;
import me.alla.homework_therecipewebsite.services.ValidatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;
    private final ValidatorService validatorService;

    public RecipeController(RecipeService recipeService, ValidatorService validatorService) {
        this.recipeService = recipeService;
        this.validatorService = validatorService;
    }

    @PostMapping
    @Operation(summary = "Добавить рецепт",
            description = "Добавление рецепта в коллекцию")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт успешно добавлен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные данные рецепта"
            )
    })
    public ResponseEntity<Long> addRecipe (@RequestBody Recipe recipe) {
        if (validatorService.isNotValid(recipe)) {
            return ResponseEntity.badRequest().build();
        }
        Long id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить рецепт",
            description = "Получение рецепта по id")
    @ApiResponses(value = {
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
                    description = "Рецепт не найден"
            )
    })
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getRecipe(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получить рецепты",
            description = "Получение всей коллекции рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепты не найдены"
            )
    })
    public ResponseEntity<Map<Long, Recipe>> getAllRecipes() {
        return ResponseEntity.of(recipeService.getAllRecipes());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить рецепт",
            description = "Редактирование рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт успешно обновлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден"
            )
    })
    public ResponseEntity<Recipe> editRecipe (@PathVariable Long id, @RequestBody Recipe recipe) {
        if (validatorService.isNotValid(recipe)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(recipeService.editRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить рецепт",
            description = "Удаление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт успешно удален",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден"
            )
    })
    public ResponseEntity<Recipe> deleteRecipe (@PathVariable Long id) {
        return ResponseEntity.of(recipeService.deleteRecipe(id));
    }
}
