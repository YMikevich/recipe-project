package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Recipe> getRecipes() {
        log.debug("RecipeServiceImpl's getRecipes()");

        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        log.debug("RecipeServiceImpl's findById()");

        return recipeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
