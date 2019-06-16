package by.mikevich.recipeproject.repositories;

import by.mikevich.recipeproject.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Recipe repository.
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
