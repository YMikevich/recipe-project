package by.mikevich.recipeproject.Service;

import by.mikevich.recipeproject.model.Recipe;

import java.util.List;
import java.util.Optional;

/**
 * The interface Recipe service.
 */
public interface RecipeService {
    /**
     * Gets recipes.
     *
     * @return the recipes
     */
    Optional<List<Recipe>> getRecipes();
}
