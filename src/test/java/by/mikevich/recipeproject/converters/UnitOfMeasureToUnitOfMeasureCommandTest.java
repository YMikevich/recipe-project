package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.UnitOfMeasureCommand;
import by.mikevich.recipeproject.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final String DESCRIPTION = "Description";
    private static final Long ID = 1L;

    private UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullable() {
        assertNull(uomConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(uomConverter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomCommand = uomConverter.convert(uom);

        //then
        assertEquals(ID, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());
    }
}