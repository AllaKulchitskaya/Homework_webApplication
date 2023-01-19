package me.alla.homework_therecipewebsite.services;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.model.Recipe;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {
    Long addRecipe(Recipe recipe);
    Optional<Recipe> getRecipe(Long id);

    Optional<Map<Long, Recipe>> getAllRecipes();

    Optional<Recipe> editRecipe(Long id, Recipe recipe);

    Optional<Recipe> deleteRecipe(Long id);

    @Nullable
    byte[] downloadRecipesText();
}
