package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.model.Recipe;
import by.mikevich.recipeproject.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    private RecipeController recipeController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void updateRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);

        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/update/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/create-recipe"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testMockMvc() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        String returnValue = "index";

        assertEquals(recipeController.getIndexPage(model), returnValue);
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());
        verify(recipeService, times(1)).getRecipes();
    }

    @Test
    public void deleteRecipe() throws Exception {
        mockMvc.perform(get("/recipe/delete/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1)).deleteRecipeById(anyLong());
    }

    @Test
    public void getRecipeForm() throws Exception {
        mockMvc.perform(get("/recipe/create-recipe"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/create-recipe"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void saveRecipe() throws Exception{
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(3L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "string")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/create-recipe"));
    }

}
