package me.alla.homework_therecipewebsite.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    @NotNull(message = "Введите корректное название рецепта")
    @NotBlank(message = "Введите корректное название рецепта")
    @NotEmpty(message = "Введите корректное название рецепта")
    private final String name;
    @Min(1)
    @NotNull(message = "Введите корректное время приготовления")
    private final Integer cookingTime;
    @NotNull(message = "Введите корректный список ингредиентов")
    private final List<Ingredient> ingredients = new ArrayList<>();
    @NotNull(message = "Введите корректный список шагов приготовления")
    private final List<String> cookingSteps = new ArrayList<>();
}
