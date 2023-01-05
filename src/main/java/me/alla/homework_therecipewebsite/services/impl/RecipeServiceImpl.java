package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.model.Recipe;
import me.alla.homework_therecipewebsite.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Integer recipeId = 0;
    private static final Map<Integer, Recipe> recipes = new HashMap<>();
    @Override
    public Integer addRecipe(Recipe recipe) {
        if (!recipes.containsValue(recipe)) {
            ++recipeId;
            recipes.put(recipeId, recipe);
        }
        return recipeId;
    }

    @Override
    public Recipe getRecipe(Integer recipeId) {
        return recipes.get(recipeId);
    }
}
