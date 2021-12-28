package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        // get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = eachUomOptional.get();
        UnitOfMeasure teaspoonUom = eachUomOptional.get();
        UnitOfMeasure dashUom = eachUomOptional.get();
        UnitOfMeasure pintUom = eachUomOptional.get();
        UnitOfMeasure cupUom = eachUomOptional.get();

        // get Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()) {
           throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Yummy Guacamole
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1. Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2. Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3. Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown. Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        guacamoleNotes.setRecipe(guacamoleRecipe);
        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom, guacamoleRecipe));
        guacamoleRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom, guacamoleRecipe));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacamoleRecipe);

        // Yummy tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spiced Grilled Chicken Taco");
        tacosRecipe.setPrepTime(9);
        tacosRecipe.setCookTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);
        tacosRecipe.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                "\n" +
                "2. Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3. Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4. Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5. Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        tacoNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getIngredients().add(new Ingredient("Ancho chili powder", new BigDecimal(2), tablespoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried oregano", new BigDecimal(1), teaspoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Dried cumin", new BigDecimal(1), teaspoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(".5"), teaspoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Clove of garlic, chopped", new BigDecimal(1), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Finely grated orange zest", new BigDecimal(1), tablespoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoonUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom, tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, tacosRecipe));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);

        return recipes;
    }

}
