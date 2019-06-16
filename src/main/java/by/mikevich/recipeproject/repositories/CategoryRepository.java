package by.mikevich.recipeproject.repositories;

import by.mikevich.recipeproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Category repository.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByDescription(String description);
}
