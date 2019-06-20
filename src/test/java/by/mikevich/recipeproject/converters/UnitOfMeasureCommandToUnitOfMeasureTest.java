package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.UnitOfMeasureCommand;
import by.mikevich.recipeproject.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final String DESCRIPTION = "Description";
    private static final Long ID = 1L;

    private UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullable() {
        assertNull(uomConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(uomConverter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(ID);
        uomCommand.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = uomConverter.convert(uomCommand);

        //then
        assertEquals(ID, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}