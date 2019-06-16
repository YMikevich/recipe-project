package by.mikevich.recipeproject.controller;

import by.mikevich.recipeproject.Service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        log.debug("IndexController's constructor");

        this.recipeService = recipeService;
    }

    @GetMapping({"/index","/", ""})
    public String getIndexPage(Model model) {
        log.debug("IndexController's getIndexPage()");

        model.addAttribute("recipes", recipeService.getRecipes().get());
        return "index";
    }
}
