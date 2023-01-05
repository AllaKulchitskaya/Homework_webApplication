package me.alla.homework_therecipewebsite.services;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.model.Recipe;

public interface RecipeService {
    Integer addRecipe(Recipe recipe);
    Recipe getRecipe(Integer recipeId);
}
