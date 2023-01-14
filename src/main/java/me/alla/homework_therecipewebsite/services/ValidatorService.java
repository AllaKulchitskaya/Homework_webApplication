package me.alla.homework_therecipewebsite.services;

import me.alla.homework_therecipewebsite.model.Ingredient;
import me.alla.homework_therecipewebsite.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ValidatorService {

    public boolean isNotValid (Recipe recipe) {
        boolean result = StringUtils.isBlank(recipe.getName()) || CollectionUtils.isEmpty(recipe.getIngredients()) ||
                CollectionUtils.isEmpty(recipe.getCookingSteps()) || recipe.getCookingTime() <= 0;
        if (!result) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                result = result || isNotValid(ingredient);
            }
        }
        return result;
    }

    public boolean isNotValid (Ingredient ingredient) {
        return StringUtils.isBlank(ingredient.getName()) || StringUtils.isBlank(ingredient.getMeasureUnit()) ||
                ingredient.getNumber() < 0;
    }
}
