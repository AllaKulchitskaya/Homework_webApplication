package me.alla.homework_therecipewebsite.services;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Long addRecipe(Recipe recipe);
    Recipe getRecipe(Long id);

    Set<Recipe> getAllRecipes();

    Recipe editRecipe(Long id, Recipe recipe);

    boolean deleteRecipe(Long id);
}
