package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.NotesCommand;
import by.mikevich.recipeproject.model.Notes;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * The type Notes to notes command.
 */
@Component
@Slf4j
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {
        log.debug("Converting Notes to NotesCommand");

        if (notes == null)
            return null;

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notes.getId());
        notesCommand.setRecipeNotes(notes.getRecipeNotes());

        return notesCommand;
    }
}
