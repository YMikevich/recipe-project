package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.CategoryCommand;
import by.mikevich.recipeproject.commands.IngredientCommand;
import by.mikevich.recipeproject.commands.NotesCommand;
import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.model.Difficulty;
import by.mikevich.recipeproject.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

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

    private RecipeCommandToRecipe recipeConverter;

    @Before
    public void setUp() throws Exception {
        recipeConverter = new RecipeCommandToRecipe(new NotesCommandToNotes(), new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    public void testNullable() {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(recipeConverter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.getIngredients().add(ingredientCommand);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDescription(RECIPE_DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setNotes(notesCommand);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        //when
        Recipe recipe = recipeConverter.convert(recipeCommand);

        //then
        assertEquals(recipeCommand.getIngredients().size(), recipe.getIngredients().size());
        assertEquals(recipeCommand.getCategories().size(), recipe.getCategories().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(RECIPE_NOTES, recipe.getNotes().getRecipeNotes());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(URL, recipe.getUrl());
        assertEquals(RECIPE_DESCRIPTION, recipe.getDescription());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
    }
}