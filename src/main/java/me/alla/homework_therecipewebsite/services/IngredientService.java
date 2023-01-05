package me.alla.homework_therecipewebsite.services;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.model.Recipe;

public interface IngredientService {
    Integer addIngredient(Ingredient ingredient);
    Ingredient getIngredient(Integer ingredientId);
}
