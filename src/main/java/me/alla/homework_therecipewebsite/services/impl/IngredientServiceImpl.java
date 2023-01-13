package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Long ingredientId = 0L;
    private static final Map<Long, Ingredient> ingredients = new HashMap<>();
    @Override
    public Long addIngredient(Ingredient ingredient) {
        if (!ingredients.containsValue(ingredient)) {
            ingredients.put(++ingredientId, ingredient);
        }
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
        return Optional.ofNullable(ingredients.replace(id, ingredient));
    }

    @Override
    public Optional<Ingredient> deleteIngredient (Long id) {
        return Optional.ofNullable(ingredients.remove(id));
    }
}
