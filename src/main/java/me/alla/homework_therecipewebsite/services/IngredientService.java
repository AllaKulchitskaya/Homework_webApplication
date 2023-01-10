package me.alla.homework_therecipewebsite.services;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.model.Recipe;

import java.util.Set;

public interface IngredientService {
    Long addIngredient(Ingredient ingredient);
    Ingredient getIngredient(Long id);

    Set<Ingredient> getAllIngredients();

    Ingredient editIngredient(Long id, Ingredient ingredient);

    boolean deleteIngredient(Long id);
}
