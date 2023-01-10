package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static Long ingredientId = 0L;
    private static final Map<Long, Ingredient> ingredients = new HashMap<>();
    @Override
    public Long addIngredient(Ingredient ingredient) {
        if (!ingredients.containsValue(ingredient)) {
            ++ingredientId;
            ingredients.put(ingredientId, ingredient);
        }
        return ingredientId;
    }

    @Override
    public Ingredient getIngredient(Long id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        }
        return null;
    }

    @Override
    public Set<Ingredient> getAllIngredients() {
        Set<Ingredient> ingredientSet = new HashSet<>();
        for (Ingredient ingredient : ingredients.values()) {
            if (ingredient != null) {
                ingredientSet.add(ingredient);
            }
        }
        return ingredientSet;
    }


    @Override
    public Ingredient editIngredient(Long id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient (Long id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return true;
        }
        return false;
    }
}
