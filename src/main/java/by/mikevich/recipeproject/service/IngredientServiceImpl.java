package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.IngredientCommand;
import by.mikevich.recipeproject.converters.IngredientCommandToIngredient;
import by.mikevich.recipeproject.converters.IngredientToIngredientCommand;
import by.mikevich.recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import by.mikevich.recipeproject.exceptions.NotFoundException;
import by.mikevich.recipeproject.model.Ingredient;
import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type Ingredient service.
 */
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    private RecipeRepository recipeRepository;

    /**
     * Instantiates a new Ingredient service.
     *
     * @param ingredientToIngredientCommand       the ingredient to ingredient command
     * @param recipeRepository                    the recipe repository
     * @param ingredientCommandToIngredient       the ingredient command to ingredient
     * @param unitOfMeasureCommandToUnitOfMeasure the unit of measure command to unit of measure
     */
    @Autowired
    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {

        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Override
    public IngredientCommand findByIngredientIdAndRecipeId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found");
            throw new NotFoundException("recipe with id " + recipeId + "not found");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();

        if (!ingredientOptional.isPresent()) {
            log.error("ingredient id not found");
            throw new NotFoundException("ingredient with recipe id " + recipeId + " and ingredient id " + ingredientId + " not found");
        }

        return ingredientOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found");
            throw new NotFoundException("recipe with id " + ingredientCommand.getRecipeId() + "not found");
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setUom(unitOfMeasureCommandToUnitOfMeasure.convert(ingredientCommand.getUom()));
        }
        else {
            Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        //save
        Optional<Ingredient> savedIngredient = savedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();

        //update
        if(!savedIngredient.isPresent()) {
            savedIngredient = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getUom().getId().equals(ingredientCommand.getUom().getId()))
                    .filter(ingredient -> ingredient.getAmount().equals(ingredientCommand.getAmount()))
                    .filter(ingredient -> ingredient.getDescription().equals(ingredientCommand.getDescription()))
                    .findFirst();
        }


        if(!savedIngredient.isPresent()) {
            log.error("ingredient id not found");
            throw new NotFoundException("ingredient with recipe id " + ingredientCommand.getRecipeId() + " and ingredient id " + ingredientCommand.getId() + " not found");
        }
        return ingredientToIngredientCommand.convert(savedIngredient.get());
    }

    @Override
    @Transactional
    public void deleteRecipeByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found");
            throw new NotFoundException("recipe with id " + recipeId + "not found");
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientToDeleteOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();

        ingredientToDeleteOptional.ifPresent(ingredient -> {
            recipe.deleteIngredient(ingredient);
            recipeRepository.save(recipe);
        });

    }
}
