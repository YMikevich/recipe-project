package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.CategoryCommand;
import by.mikevich.recipeproject.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final String DESCRIPTION = "Description";
    private static final Long ID = 1L;

    private CategoryToCategoryCommand categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullable() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryConverter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = categoryConverter.convert(category);

        //then
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}