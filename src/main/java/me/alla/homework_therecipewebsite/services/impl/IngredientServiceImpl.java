package me.alla.homework_therecipewebsite.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.services.IngredientFilesService;
import me.alla.homework_therecipewebsite.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Long ingredientId = 0L;
    private static Map<Long, Ingredient> ingredients = new HashMap<>();
    private final IngredientFilesService ingredientFilesService;

    public IngredientServiceImpl(IngredientFilesService ingredientFilesService) {
        this.ingredientFilesService = ingredientFilesService;
    }

    @Override
    public Long addIngredient(Ingredient ingredient) {
        if (!ingredients.containsValue(ingredient)) {
            ingredients.put(++ingredientId, ingredient);
        }
        saveToFile();
        return ingredientId;
    }

    @Override
    public Optional<Ingredient> getIngredient(Long id) {
        return Optional.ofNullable(ingredients.get(id));
    }

    @Override
    public Optional<Map<Long, Ingredient>> getAllIngredients() {
        return Optional.ofNullable(new HashMap<>(ingredients));
    }


    @Override
    public Optional<Ingredient> editIngredient(Long id, Ingredient ingredient) {
        Optional<Ingredient> result = Optional.ofNullable(ingredients.replace(id, ingredient));
        saveToFile();
        return result;
    }

    @Override
    public Optional<Ingredient> deleteIngredient (Long id) {
        Optional<Ingredient> result = Optional.ofNullable(ingredients.remove(id));
        saveToFile();
        return result;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = ingredientFilesService.readFromFile();
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }
}
