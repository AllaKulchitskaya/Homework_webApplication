package me.alla.homework_therecipewebsite.controllers;

import jakarta.validation.Valid;
import me.alla.homework_therecipewebsite.model.Recipe;
import me.alla.homework_therecipewebsite.services.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public Integer addRecipe (@Valid @RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        return recipeService.getRecipe(id);
    }
}
