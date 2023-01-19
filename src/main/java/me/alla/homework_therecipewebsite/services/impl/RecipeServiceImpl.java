package me.alla.homework_therecipewebsite.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.alla.homework_therecipewebsite.model.Recipe;
import me.alla.homework_therecipewebsite.services.RecipeFilesService;
import me.alla.homework_therecipewebsite.services.RecipeService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Long recipeId = 0L;
    private static Map<Long, Recipe> recipes = new HashMap<>();
    private final RecipeFilesService recipeFilesService;

    public RecipeServiceImpl(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }

    @Override
    public Long addRecipe(Recipe recipe) {
        if (!recipes.containsValue(recipe)) {
            recipes.put(++recipeId, recipe);
        }
        saveToFile();
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
        Optional<Recipe> result = Optional.ofNullable(recipes.replace(id, recipe));
        saveToFile();
        return result;
    }

    @Override
    public Optional<Recipe> deleteRecipe(Long id) {
        Optional<Recipe> result = Optional.ofNullable(recipes.remove(id));
        saveToFile();
        return result;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            recipeFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = recipeFilesService.readFromFile();
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Nullable
    @Override
    public byte[] downloadRecipesText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Recipe recipe : recipes.values()) {
            stringBuilder.append(recipe).append("\n").append("***").append("\n");
        }
        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
