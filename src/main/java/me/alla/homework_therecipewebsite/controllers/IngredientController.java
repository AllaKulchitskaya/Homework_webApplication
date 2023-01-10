package me.alla.homework_therecipewebsite.controllers;

import jakarta.validation.Valid;
import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Long> addIngredient (@Valid @RequestBody Ingredient ingredient) {
        Long id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Ingredient>> getAllIngredients() {
        Set<Ingredient> ingredientSet = ingredientService.getAllIngredients();
        if (ingredientSet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredientSet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient (@PathVariable Long id, @Valid @RequestBody Ingredient ingredient) {
        Ingredient editedIngredient = ingredientService.editIngredient(id, ingredient);
        if (editedIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient (@PathVariable Long id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
