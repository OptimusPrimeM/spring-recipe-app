package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.RecipeCommand;
import com.optimusprime.springrecipeapp.domain.Category;
import com.optimusprime.springrecipeapp.domain.Ingredient;
import com.optimusprime.springrecipeapp.domain.Notes;
import com.optimusprime.springrecipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
    private static final  Long RECIPE_ID = 1L;
    private static final String DESCRIPTION = "My Recipe";
    private static final Integer PREP_TIME = Integer.valueOf("5");
    private static final Integer COOK_TIME=Integer.valueOf("7");
    private static final Integer SERVING = Integer.valueOf("3");
    private static final String SOURCE = "source";
    private static final String URL = "Some url";
    private static final String DIRECTIONS = "Direction";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }
    @Test
    public void convert() throws Exception {

        //given
        Recipe  recipe= new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setUrl(URL);
        recipe.setServing(SERVING);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDirections(DIRECTIONS);
        recipe.setDescription(DESCRIPTION);
        recipe.setSource(SOURCE);
        recipe.setCookTime(COOK_TIME);

        Ingredient ingredient= new Ingredient();
        ingredient.setId(INGRED_ID_1);

        Ingredient ingredient1= new Ingredient();
        ingredient1.setId(INGRED_ID_2);

        Category category= new Category();
        category.setId(CAT_ID_1);

        Category category1= new Category();
        category1.setId(CAT_ID2);

        Notes notes= new Notes();
        notes.setId(NOTES_ID);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category1);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient1);

        recipe.setNotes(notes);


        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertEquals(RECIPE_ID,recipeCommand.getId());
        assertEquals(URL,recipeCommand.getUrl());
        assertEquals(SERVING,recipeCommand.getServing());
        assertEquals(PREP_TIME,recipeCommand.getPrepTime());
        assertEquals(DIRECTIONS,recipeCommand.getDirections());
        assertEquals(DESCRIPTION,recipeCommand.getDescription());
        assertEquals(SOURCE,recipeCommand.getSource());
        assertEquals(SOURCE,recipeCommand.getSource());
        assertEquals(NOTES_ID,recipeCommand.getNotes().getId());
        assertEquals(2,recipeCommand.getIngredients().size());
        assertEquals(2,recipeCommand.getCategories().size());
    }

}