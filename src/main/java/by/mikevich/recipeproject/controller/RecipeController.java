package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
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
}
