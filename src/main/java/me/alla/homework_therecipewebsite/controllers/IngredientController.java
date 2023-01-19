package me.alla.homework_therecipewebsite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.services.IngredientService;
import me.alla.homework_therecipewebsite.services.ValidatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпоинты для работы с ингредиентами")
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private final ValidatorService validatorService;

    public IngredientController(IngredientService ingredientService, ValidatorService validatorService) {
        this.ingredientService = ingredientService;
        this.validatorService = validatorService;
    }

    @PostMapping
    @Operation(summary = "Добавить ингредиент",
            description = "Добавление ингредиента в коллекцию")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент успешно добавлен"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некорректные данные ингредиента"
            )
    })
    public ResponseEntity<Long> addIngredient (@RequestBody Ingredient ingredient) {
        if (validatorService.isNotValid(ingredient)) {
            return ResponseEntity.badRequest().build();
        }
        Long id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить ингредиент",
            description = "Получение ингредиента по id")
    @ApiResponses(value = {
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
                    description = "Ингредиент не найден"
            )
    })
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.getIngredient(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получить ингредиенты",
            description = "Получение всей коллекции ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиенты не найдены"
            )
    })
    public ResponseEntity<Map<Long, Ingredient>> getAllIngredients() {
       return ResponseEntity.of(ingredientService.getAllIngredients());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить ингредиент",
            description = "Редактирование ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент успешно обновлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент не найден"
            )
    })
    public ResponseEntity<Ingredient> editIngredient (@PathVariable Long id, @RequestBody Ingredient ingredient) {
        if (validatorService.isNotValid(ingredient)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(ingredientService.editIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить ингредиент",
            description = "Удаление ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент успешно удален"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ингредиент не найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            )
    })
    public ResponseEntity<Ingredient> deleteIngredient (@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.deleteIngredient(id));
    }
}
