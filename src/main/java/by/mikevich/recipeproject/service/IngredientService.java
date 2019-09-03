package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.IngredientCommand;

/**
 * The interface Ingredient service.
 */
public interface IngredientService {
    /**
     * Find by ingredient id and recipe id ingredient command.
     *
     * @param recipeId     the recipe id
     * @param ingredientId the ingredient id
     * @return the ingredient command
     */
    IngredientCommand findByIngredientIdAndRecipeId(Long recipeId, Long ingredientId);

    /**
     * Save ingredient command ingredient command.
     *
     * @param recipeCommand the recipe command
     * @return the ingredient command
     */
    IngredientCommand saveIngredientCommand(IngredientCommand recipeCommand);

    /**
     * Delete recipe by recipe id and ingredient id.
     *
     * @param recipeId     the recipe id
     * @param ingredientId the ingredient id
     */
    void deleteRecipeByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
