package by.mikevich.recipeproject.Service;

import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Recipe service.
 */

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    /**
     * Instantiates a new Recipe service.
     *
     * @param recipeRepository the recipe repository
     */
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        log.debug("RecipeServiceImpl's constructor");

        this.recipeRepository = recipeRepository;
    }

    @Override
    public Optional<List<Recipe>> getRecipes() {
        log.debug("RecipeServiceImpl's getRecipes()");

        return Optional.of(recipeRepository.findAll());
    }
}
