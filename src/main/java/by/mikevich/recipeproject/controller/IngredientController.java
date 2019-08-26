package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.commands.IngredientCommand;
import by.mikevich.recipeproject.commands.RecipeCommand;
import by.mikevich.recipeproject.commands.UnitOfMeasureCommand;
import by.mikevich.recipeproject.service.IngredientService;
import by.mikevich.recipeproject.service.RecipeService;
import by.mikevich.recipeproject.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/ingredients/{recipeId}")
    public String listIngredients(@PathVariable Long recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(recipeId));

        return "recipe/ingredients/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/show/{ingredientId}")
    public String showIngredient(@PathVariable Long recipeId,
                                 @PathVariable Long ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findByIngredientIdAndRecipeId(recipeId, ingredientId));

        return "recipe/ingredients/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/update/{ingredientId}")
    public String updateIngredient(@PathVariable Long recipeId,
                                   @PathVariable Long ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findByIngredientIdAndRecipeId(recipeId, ingredientId));
        model.addAttribute("uomList", uomService.listAllUoms());

        return "/recipe/ingredients/create-ingredient";
    }

    @GetMapping("/recipe/{recipeId}/ingredients/create-ingredient")
    public String createIngredient(@PathVariable Long recipeId, Model model) {

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(recipeId);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);


        model.addAttribute("ingredient", ingredientCommand);
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", uomService.listAllUoms());

        return "/recipe/ingredients/create-ingredient";
    }

    @PostMapping("/recipe/{recipeId}/ingredients")
    public String updateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + savedIngredientCommand.getRecipeId() + "/ingredients/show/" + savedIngredientCommand.getId();
    }

    @GetMapping("/recipe/{recipeId}/ingredients/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable Long recipeId,
                                   @PathVariable Long ingredientId) {

        ingredientService.deleteRecipeByRecipeIdAndIngredientId(recipeId, ingredientId);
        return "redirect:/recipe" + "/ingredients/" + recipeId;
    }
}
