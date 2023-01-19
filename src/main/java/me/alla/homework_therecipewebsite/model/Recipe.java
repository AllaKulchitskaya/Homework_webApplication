package me.alla.homework_therecipewebsite.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private String name;
    private Integer cookingTime;
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<String> cookingSteps = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append("\n")
                .append("Время приготовления: ").append(cookingTime).append(" минут.").append("\n")
                .append("Ингредиенты: ").append("\n");
        for (Ingredient ingredient : ingredients){
            stringBuilder.append("• ").append(ingredient).append("\n");
        }
        stringBuilder.append("Инструкция приготовления: ").append("\n");
        for (String cookingStep : cookingSteps) {
            stringBuilder.append(cookingStep).append("\n");
        }
        return stringBuilder.toString();
    }
}
