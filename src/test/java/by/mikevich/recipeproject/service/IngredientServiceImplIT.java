package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.IngredientCommand;
import by.mikevich.recipeproject.converters.IngredientToIngredientCommand;
import by.mikevich.recipeproject.model.Ingredient;
import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IngredientServiceImplIT {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private IngredientToIngredientCommand ingredientToIngredientCommand;

    @Autowired
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void findByIngredientIdAndRecipeId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        IngredientCommand ingredientCommand = ingredientService.findByIngredientIdAndRecipeId(1L, 2L);

        //then
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        assertEquals(Long.valueOf(2L), ingredientCommand.getId());
    }
}