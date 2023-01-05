package me.alla.homework_therecipewebsite.controllers;

import jakarta.validation.Valid;
import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.services.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Integer addIngredient (@Valid @RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable Integer id) {
        return ingredientService.getIngredient(id);
    }
}
