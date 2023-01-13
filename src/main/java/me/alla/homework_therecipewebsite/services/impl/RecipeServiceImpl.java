package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.model.Recipe;
import me.alla.homework_therecipewebsite.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Long recipeId = 0L;
    private static final Map<Long, Recipe> recipes = new HashMap<>();
    @Override
    public Long addRecipe(Recipe recipe) {
        if (!recipes.containsValue(recipe)) {
            recipes.put(++recipeId, recipe);
        }
        return recipeId;
    }

    @Override
    public Optional<Recipe> getRecipe(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    @Override
    public Optional<Map<Long, Recipe>> getAllRecipes() {
        return Optional.ofNullable(new HashMap<>(recipes));
    }

    @Override
    public Optional<Recipe> editRecipe(Long id, Recipe recipe) {
        return Optional.ofNullable(recipes.replace(id, recipe));
    }

    @Override
    public Optional<Recipe> deleteRecipe(Long id) {
        return Optional.ofNullable(recipes.remove(id));
    }
}
