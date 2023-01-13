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
}
