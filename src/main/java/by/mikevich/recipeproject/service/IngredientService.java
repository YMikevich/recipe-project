package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByIngredientIdAndRecipeId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand recipeCommand);
    void deleteRecipeByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
