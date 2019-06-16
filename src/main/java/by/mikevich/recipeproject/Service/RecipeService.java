package by.mikevich.recipeproject.Service;

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
}
