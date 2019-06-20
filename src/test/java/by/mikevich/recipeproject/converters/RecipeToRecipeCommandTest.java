package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private static final Long CATEGORY_ID = 1L;
    private final static Long INGREDIENT_ID = 3L;
    private final static Long NOTES_ID = 9L;
    private final static String RECIPE_NOTES = "Recipe Notes";
    private final static Long RECIPE_ID = 5L;
    private final static String SOURCE = "Source";
    private final static String DIRECTIONS = "Directions";
    private final static String URL = "url";
    private final static Integer SERVINGS = 2;
    private final static Difficulty DIFFICULTY = Difficulty.EASY;
    private final static Integer COOK_TIME = 10;
    private final static Integer PREP_TIME = 11;
    private final static String RECIPE_DESCRIPTION = "Recipe Description";

    private RecipeToRecipeCommand recipeConverter;

    @Before
    public void setUp() throws Exception {
        recipeConverter = new RecipeToRecipeCommand(new NotesToNotesCommand(), new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void testNullable() {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(recipeConverter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);

        Category category = new Category();
        category.setId(CATEGORY_ID);

        Recipe recipe = new Recipe();
        recipe.getCategories().add(category);
        recipe.getIngredients().add(ingredient);
        recipe.setCookTime(COOK_TIME);
        recipe.setDescription(RECIPE_DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setId(RECIPE_ID);
        recipe.setNotes(notes);
        recipe.setPrepTime(PREP_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        //when
        RecipeCommand recipeCommand = recipeConverter.convert(recipe);

        //then
        assertEquals(recipe.getIngredients().size(), recipeCommand.getIngredients().size());
        assertEquals(recipe.getCategories().size(), recipeCommand.getCategories().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(RECIPE_NOTES, recipeCommand.getNotes().getRecipeNotes());
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(RECIPE_DESCRIPTION, recipeCommand.getDescription());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
    }
}