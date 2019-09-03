package by.mikevich.recipeproject.service;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.converters.RecipeCommandToRecipe;
import by.mikevich.recipeproject.converters.RecipeToRecipeCommand;
import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecipeServiceImplIT {

    private final static String DESCRIPTION = "Description";

    @Autowired
    RecipeServiceImpl recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;


    @Test
    public void saveRecipeCommand() {

        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Iterator<Recipe> it = recipes.iterator();
        Recipe recipeFromRepository = it.next();

        //when
        recipeFromRepository.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeToRecipeCommand.convert(recipeFromRepository));

        //then
        assertEquals(recipeFromRepository.getId(), savedRecipeCommand.getId());
        assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
    }

    @Test
    @Transactional(readOnly = true)
    public void findByIdTest() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Iterator<Recipe> it = recipes.iterator();
        Recipe recipeFromRepository = it.next();

        //when
        Recipe recipeFound = recipeService.findById(recipeFromRepository.getId());

        //then
        assertNotNull("Null recipe returned",recipeFound);
        assertEquals(recipeFound, recipeFromRepository);
    }

    @Test
    @Transactional(readOnly = true)
    public void getRecipesTest() {

        assertEquals(recipeRepository.findAll().size(), recipeService.getRecipes().size());
    }

    @Test
    public void deleteRecipeTest() {

        //when
        recipeRepository.findAll().stream()
                .forEach(recipe -> recipeService.deleteRecipeById(recipe.getId()));

        assertEquals(0L, recipeRepository.findAll().size());
    }

    @Test
    @Transactional(readOnly = true)
    public void findRecipeCommandById() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Iterator<Recipe> it = recipes.iterator();
        Recipe recipeFromRepository = it.next();

        //when
        Recipe recipeFound = recipeCommandToRecipe.convert(recipeService.findRecipeCommandById(recipeFromRepository.getId())) ;

        //then
        assertNotNull("Null recipe returned",recipeFound);
        assertEquals(recipeFound, recipeFromRepository);
    }

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}