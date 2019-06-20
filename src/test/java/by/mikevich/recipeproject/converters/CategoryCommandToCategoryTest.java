package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.CategoryCommand;
import by.mikevich.recipeproject.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final String DESCRIPTION = "Description";
    private static final Long ID = 1L;

    private CategoryCommandToCategory categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullable() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryConverter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category = categoryConverter.convert(categoryCommand);

        //then
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}