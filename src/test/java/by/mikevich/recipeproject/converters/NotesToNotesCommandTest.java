package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.NotesCommand;
import by.mikevich.recipeproject.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    private final static Long ID = 1L;
    private final static String RECIPE_NOTES = "Description";

    private NotesToNotesCommand notesConverter;

    @Before
    public void setUp() throws Exception {
        notesConverter = new NotesToNotesCommand();
    }

    @Test
    public void testNullable() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(notesConverter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand = notesConverter.convert(notes);

        //then
        assertEquals(ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}