package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * The interface Unit of measure service.
 */
public interface UnitOfMeasureService {
    /**
     * List all uoms set.
     *
     * @return the set
     */
    Set<UnitOfMeasureCommand> listAllUoms();
}
