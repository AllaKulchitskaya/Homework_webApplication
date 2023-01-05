package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Integer ingredientId = 0;
    private static final Map<Integer, Ingredient> ingredients = new HashMap<>();
    @Override
    public Integer addIngredient(Ingredient ingredient) {
        if (!ingredients.containsValue(ingredient)) {
            ++ingredientId;
            ingredients.put(ingredientId, ingredient);
        }
        return ingredientId;
    }

    @Override
    public Ingredient getIngredient(Integer ingredientId) {
        if (ingredients.containsKey(ingredientId)) {
            return ingredients.get(ingredientId);
        }
        return null;
    }
}
