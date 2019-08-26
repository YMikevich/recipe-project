package by.mikevich.recipeproject.converters;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.model.Recipe;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * The type Recipe command to recipe.
 */
@Component
@Slf4j
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private NotesCommandToNotes notesConverter;
    private CategoryCommandToCategory categoryConverter;
    private IngredientCommandToIngredient ingredientConverter;

    /**
     * Instantiates a new Recipe command to recipe.
     *
     * @param notesConverter      the notes converter
     * @param categoryConverter   the category converter
     * @param ingredientConverter the ingredient converter
     */
    @Autowired
    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        log.debug("Converting RecipeCommand to Recipe");

        if (recipeCommand == null)
            return null;

        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setSource(recipeCommand.getSource());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setServings(recipeCommand.getServings());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setImage(recipeCommand.getImage());
        recipe.setDescription(recipeCommand.getDescription());

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
