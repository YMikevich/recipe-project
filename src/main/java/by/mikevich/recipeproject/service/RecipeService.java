package by.mikevich.recipeproject.service;

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
    Recipe findById(Long id);
}
