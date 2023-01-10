package me.alla.homework_therecipewebsite.controllers;

import jakarta.validation.Valid;
import me.alla.homework_therecipewebsite.model.Recipe;
import me.alla.homework_therecipewebsite.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Long> addRecipe (@Valid @RequestBody Recipe recipe) {
        Long id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Recipe>> getAllRecipes() {
        Set<Recipe> recipeSet = recipeService.getAllRecipes();
        if (recipeSet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipeSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe (@PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        Recipe editedRecipe = recipeService.editRecipe(id, recipe);
        if (editedRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe (@PathVariable Long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
