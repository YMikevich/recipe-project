package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.NotesCommand;
import by.mikevich.recipeproject.model.Notes;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * The type Notes command to notes.
 */
@Component
@Slf4j
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        log.debug("Converting NotesCommand to Notes");

        if (notesCommand == null)
            return null;

        Notes notes = new Notes();
        notes.setId(notesCommand.getId());
        notes.setRecipeNotes(notesCommand.getRecipeNotes());

        return notes;
    }
}
