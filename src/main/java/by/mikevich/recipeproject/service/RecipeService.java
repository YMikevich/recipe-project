package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.model.Recipe;

import java.util.List;

/**
 * The interface Recipe service.
 */
public interface RecipeService {
    /**
     * Gets recipes.
     *
     * @return the recipes
     */
    List<Recipe> getRecipes();

    /**
     * Find by id recipe.
     *
     * @param id the id
     * @return the recipe
     */
    Recipe findById(Long id);

    /**
     * Save recipe command recipe command.
     *
     * @param recipeCommand the recipe command
     * @return the recipe command
     */
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findRecipeCommandById(Long id);

    void deleteRecipeById(Long id);
}
