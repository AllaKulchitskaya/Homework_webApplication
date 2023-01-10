package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.model.Recipe;
import me.alla.homework_therecipewebsite.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Long recipeId = 0L;
    private static final Map<Long, Recipe> recipes = new HashMap<>();
    @Override
    public Long addRecipe(Recipe recipe) {
        if (!recipes.containsValue(recipe)) {
            ++recipeId;
            recipes.put(recipeId, recipe);
        }
        return recipeId;
    }

    @Override
    public Recipe getRecipe(Long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        }
        return null;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        for (Recipe recipe : recipes.values()) {
            if (recipe != null) {
                recipeSet.add(recipe);
            }
        }
        return recipeSet;
    }

    @Override
    public Recipe editRecipe(Long id, Recipe recipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(Long id) {
        if (recipes.containsKey(id)) {
            recipes.remove(id);
            return true;
        }
        return false;
    }
}
