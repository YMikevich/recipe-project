package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * The type Recipe controller.
 */
@Controller
@Slf4j
public class RecipeController {

    private RecipeService recipeService;

    /**
     * Instantiates a new Recipe controller.
     *
     * @param recipeService the recipe service
     */
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    /**
     * Gets recipe by id.
     *
     * @param model    the model
     * @param recipeId the recipe id
     * @return the recipe by id
     */
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

    /**
     * Gets update page.
     *
     * @param id    the id
     * @param model the model
     * @return the update page
     */
    @GetMapping("/recipe/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));

        return "recipe/create-recipe";
    }

    /**
     * Delete recipe string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);

        return "redirect:/";
    }

    /**
     * Gets recipe form.
     *
     * @param model the model
     * @return the recipe form
     */
    @GetMapping("/recipe/create-recipe")
    public String getRecipeForm(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/create-recipe";
    }

    /**
     * Save or update recipe string.
     *
     * @param recipeCommand the recipe command
     * @param bindingResult the binding result
     * @return the string
     */
    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return "recipe/create-recipe";
        }

        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipe/show/" + savedRecipeCommand.getId();
    }
}
