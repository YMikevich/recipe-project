package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.converters.RecipeCommandToRecipe;
import by.mikevich.recipeproject.converters.RecipeToRecipeCommand;
import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Recipe service.
 */
@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private RecipeCommandToRecipe recipeCommandToRecipe;

    /**
     * Instantiates a new Recipe service.
     *
     * @param recipeRepository      the recipe repository
     * @param recipeToRecipeCommand the recipe to recipe command
     * @param recipeCommandToRecipe the recipe command to recipe
     */
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        log.debug("RecipeServiceImpl's DI constructor");

        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public List<Recipe> getRecipes() {
        log.debug("RecipeServiceImpl's getRecipes()");

        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        log.debug("RecipeServiceImpl's findById()");

        return recipeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        log.debug("RecipeServiceImpl's saveRecipeCommand()");

        Recipe savedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        recipeRepository.save(savedRecipe);

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long id) {
        log.debug("Finding recipe by id " + id);

        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public void deleteRecipeById(Long id) {
        log.debug("Deleting recipe by id " + id);

        recipeRepository.deleteById(id);
    }
}
