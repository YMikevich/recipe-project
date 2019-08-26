package by.mikevich.recipeproject.repositories;

import by.mikevich.recipeproject.model.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Unit of measure repository.
 */
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {
    /**
     * Find unit of measure by description optional.
     *
     * @param description the description
     * @return the optional
     */
    Optional<UnitOfMeasure> findUnitOfMeasureByDescription(String description);
}
