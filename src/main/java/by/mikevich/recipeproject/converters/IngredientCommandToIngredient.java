package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.IngredientCommand;
import by.mikevich.recipeproject.model.Ingredient;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * The type Ingredient command to ingredient.
 */
@Component
@Slf4j
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter;

    /**
     * Instantiates a new Ingredient command to ingredient.
     *
     * @param unitOfMeasureConverter the unit of measure converter
     */
    @Autowired
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter) {
        this.unitOfMeasureConverter = unitOfMeasureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        log.debug("Converting IngredientCommand to Ingredient");

        if (ingredientCommand == null)
            return null;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setUom(unitOfMeasureConverter.convert(ingredientCommand.getUom()));
        ingredient.setAmount(ingredientCommand.getAmount());

        return ingredient;
    }
}
