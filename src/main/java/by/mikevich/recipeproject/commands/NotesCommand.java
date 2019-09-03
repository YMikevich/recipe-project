package by.mikevich.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Notes command.
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {

    private Long id;
    private String recipeNotes;
}
