package me.alla.homework_therecipewebsite.services;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.model.Recipe;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface IngredientService {
    Long addIngredient(Ingredient ingredient);
    Optional<Ingredient> getIngredient(Long id);

    Optional<Map<Long, Ingredient>> getAllIngredients();

    Optional<Ingredient> editIngredient(Long id, Ingredient ingredient);

    Optional<Ingredient> deleteIngredient(Long id);
}
