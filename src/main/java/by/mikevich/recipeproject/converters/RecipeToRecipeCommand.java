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
 * The type Recipe to recipe command.
 */
@Component
@Slf4j
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private NotesToNotesCommand notesConverter;
    private CategoryToCategoryCommand categoryConverter;
    private IngredientToIngredientCommand ingredientConverter;

    /**
     * Instantiates a new Recipe to recipe command.
     *
     * @param notesConverter      the notes converter
     * @param categoryConverter   the category converter
     * @param ingredientConverter the ingredient converter
     */
    @Autowired
    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        log.debug("Converting Recipe to RecipeCommand");

        if (recipe == null)
            return null;

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setDescription(recipe.getDescription());

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories()
                    .forEach( category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipeCommand;
    }
}
