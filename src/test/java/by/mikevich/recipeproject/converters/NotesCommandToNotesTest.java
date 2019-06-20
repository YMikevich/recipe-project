package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.NotesCommand;
import by.mikevich.recipeproject.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private final static Long ID = 1L;
    private final static String RECIPE_NOTES = "Description";

    private NotesCommandToNotes notesConverter;

    @Before
    public void setUp() throws Exception {
        notesConverter = new NotesCommandToNotes();
    }

    @Test
    public void testNullable() {
        assertNull(notesConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(notesConverter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = notesConverter.convert(notesCommand);

        //then
        assertEquals(ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}