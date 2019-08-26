package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/show/{recipe-id}")
    public String getRecipeById(Model model, @PathVariable(name = "recipe-id") Long recipeId) {

        model.addAttribute("recipe", recipeService.findById(recipeId));

        return "recipe/show";
    }

    /**
     * Gets index page.
     *
     * @param model the model
     * @return the index page
     */
    @GetMapping({"/index","/", ""})
    public String getIndexPage(Model model) {
        log.debug("RecipeController's getIndexPage()");

        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }

    @GetMapping("/recipe/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));

        return "recipe/create-recipe";
    }

    @GetMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);

        return "redirect:/";
    }

    @GetMapping("/recipe/create-recipe")
    public String getRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/create-recipe";
    }

    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }
}
