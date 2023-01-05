package me.alla.homework_therecipewebsite.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    @NotNull(message = "Введите корректное название ингредиента")
    @NotEmpty(message = "Введите корректное название ингредиента")
    @NotBlank(message = "Введите корректное название ингредиента")
    private final String name;
    @Min(0)
    @NotNull(message = "Введите корректное количество ингредиента")
    private Integer number;
    @NotNull(message = "Введите корректную единицу измерения")
    @NotEmpty(message = "Введите корректную единицу измерения")
    @NotBlank(message = "Введите корректную единицу измерения")
    private String measureUnit;
}
