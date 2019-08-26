package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.IngredientCommand;
import by.mikevich.recipeproject.model.Ingredient;
import by.mikevich.recipeproject.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private final static Long INGREDIENT_ID = 1L;
    private final static String INGREDIENT_DESCRIPTION = "Ingredient Description";
    private final static BigDecimal AMOUNT = new BigDecimal(1);
    private final static Long UOM_ID = 2L;
    private final static String UOM_DESCRIPTION = "UOM Description";

    private IngredientToIngredientCommand ingredientConverter;

    @Before
    public void setUp() throws Exception {
        ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullable() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(INGREDIENT_DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(uom);

        //when
        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);

        //then
        assertEquals(INGREDIENT_ID, ingredientCommand.getId());
        assertEquals(INGREDIENT_DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());
        assertEquals(UOM_DESCRIPTION, ingredientCommand.getUom().getDescription());
    }
}