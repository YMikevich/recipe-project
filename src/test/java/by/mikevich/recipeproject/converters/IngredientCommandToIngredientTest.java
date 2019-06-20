package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.IngredientCommand;
import by.mikevich.recipeproject.commands.UnitOfMeasureCommand;
import by.mikevich.recipeproject.model.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private final static Long INGREDIENT_ID = 1L;
    private final static String INGREDIENT_DESCRIPTION = "Ingredient Description";
    private final static BigDecimal AMOUNT = new BigDecimal(1);
    private final static Long UOM_ID = 2L;
    private final static String UOM_DESCRIPTION = "UOM Description";

    private IngredientCommandToIngredient ingredientConverter;

    @Before
    public void setUp() throws Exception {
        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullable() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        uomCommand.setDescription(UOM_DESCRIPTION);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setDescription(INGREDIENT_DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUnitOfMeasure(uomCommand);

        //when
        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);

        //then
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(INGREDIENT_DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
        assertEquals(UOM_DESCRIPTION, ingredient.getUnitOfMeasure().getDescription());
    }
}