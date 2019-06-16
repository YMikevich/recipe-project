package by.mikevich.recipeproject.bootstrap;

import by.mikevich.recipeproject.model.*;
import by.mikevich.recipeproject.repositories.CategoryRepository;
import by.mikevich.recipeproject.repositories.RecipeRepository;
import by.mikevich.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        log.debug("RecipeBootsTrap's constructor");

        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());

        log.debug("Saving recipes into the repo in RecipeBootstrap");
    }

    private List<Recipe> getRecipes() {

        final String guacamoleUrl = "https://www.simplyrecipes.com/recipes/perfect_guacamole/";
        Recipe guacamoleRecipe = new Recipe();

        Optional<UnitOfMeasure> each = unitOfMeasureRepository.findUnitOfMeasureByDescription("Each");
        if (!each.isPresent()){
            throw new RuntimeException("UOM is not found");
        }

        Optional<UnitOfMeasure> tbsp = unitOfMeasureRepository.findUnitOfMeasureByDescription("Tbsp");
        if (!tbsp.isPresent()){
            throw new RuntimeException("UOM is not found");
        }

        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        if (!teaspoon.isPresent()){
            throw new RuntimeException("UOM is not found");
        }

        Optional<UnitOfMeasure> dash = unitOfMeasureRepository.findUnitOfMeasureByDescription("Teaspoon");
        if (!dash.isPresent()){
            throw new RuntimeException("UOM is not found");
        }

        guacamoleRecipe.addIngredient(new Ingredient("Ripe Avocado", BigDecimal.valueOf(2), each.get()));
        guacamoleRecipe.addIngredient(new Ingredient("Kosher Salt", new BigDecimal(.5), teaspoon.get()));
        guacamoleRecipe.addIngredient(new Ingredient("Lemon Juice", BigDecimal.valueOf(1), tbsp.get()));
        guacamoleRecipe.addIngredient(new Ingredient("Minced Red Onion", BigDecimal.valueOf(2), tbsp.get()));
        guacamoleRecipe.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed, minced", BigDecimal.valueOf(2), each.get()));
        guacamoleRecipe.addIngredient(new Ingredient("Cilantro(leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tbsp.get()));
        guacamoleRecipe.addIngredient(new Ingredient("Freshly Grated Black Pepper", BigDecimal.valueOf(1), dash.get()));
        guacamoleRecipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(.5), each.get()));

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Guacamole, a dip made from avocados, is originally from Mexico. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).\n" +
                "\n" +
                "MAKING GUACAMOLE IS EASY\n" +
                "All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "\n" +
                "Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. You can get creative with homemade guacamole!\n" +
                "\n" +
                "GUACAMOLE TIP: USE RIPE AVOCADOS\n" +
                "The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n" +
                "\n" +
                "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.");

        Optional<Category> dip = categoryRepository.findCategoryByDescription("dip");
        if (!dip.isPresent()){
            throw new RuntimeException("Category is not found");
        }

        Optional<Category> mexican = categoryRepository.findCategoryByDescription("Mexican");
        if (!mexican.isPresent()){
            throw new RuntimeException("Category is not found");
        }

        Optional<Category> avocado = categoryRepository.findCategoryByDescription("Avocado");
        if (!avocado.isPresent()){
            throw new RuntimeException("Category is not found");
        }

        Optional<Category> vegan = categoryRepository.findCategoryByDescription("Vegan");
        if (!vegan.isPresent()){
            throw new RuntimeException("Category is not found");
        }

        guacamoleRecipe.addCategory(dip.get());
        guacamoleRecipe.addCategory(mexican.get());
        guacamoleRecipe.addCategory(avocado.get());
        guacamoleRecipe.addCategory(vegan.get());

        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setDescription("Guacamole");
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setNotes(guacamoleNotes);
        guacamoleRecipe.setServings(3);
        guacamoleRecipe.setUrl(guacamoleUrl);
        guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado" +
                " with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n2 Mash with" +
                " a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n3 Add salt, lime juice," +
                " and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the" +
                " avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili" +
                " pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air" +
                " causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(guacamoleRecipe);

        log.debug("Guacamole recipe constructed");

        return recipes;
    }
}
