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
 * The type Ingredient to ingredient command.
 */
@Component
@Slf4j
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter;

    /**
     * Instantiates a new Ingredient to ingredient command.
     *
     * @param unitOfMeasureConverter the unit of measure converter
     */
    @Autowired
    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter) {
        this.unitOfMeasureConverter = unitOfMeasureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        log.debug("Converting Ingredient to IngredientCommand");

        if (ingredient == null)
            return null;

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());

        if (ingredient.getRecipe() != null) {
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }

        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUom(unitOfMeasureConverter.convert(ingredient.getUom()));

        return ingredientCommand;
    }
}
