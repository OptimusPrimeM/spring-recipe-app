package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.CategoryCommand;
import com.optimusprime.springrecipeapp.commands.IngredientCommand;
import com.optimusprime.springrecipeapp.commands.NotesCommand;
import com.optimusprime.springrecipeapp.commands.RecipeCommand;
import com.optimusprime.springrecipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

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

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }
    @Test
    public void convert() throws Exception {

        //given
       RecipeCommand recipeCommand= new RecipeCommand();
       recipeCommand.setId(RECIPE_ID);
       recipeCommand.setUrl(URL);
       recipeCommand.setServing(SERVING);
       recipeCommand.setPrepTime(PREP_TIME);
       recipeCommand.setDirections(DIRECTIONS);
       recipeCommand.setDescription(DESCRIPTION);
       recipeCommand.setSource(SOURCE);
       recipeCommand.setCookTime(COOK_TIME);

        IngredientCommand ingredientCommand= new IngredientCommand();
        ingredientCommand.setId(INGRED_ID_1);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_2);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID_1);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID2);

        NotesCommand notesCommand= new NotesCommand();
        notesCommand.setId(NOTES_ID);

        recipeCommand.getIngredients().add(ingredientCommand);
        recipeCommand.getIngredients().add(ingredientCommand1);

        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.getCategories().add(categoryCommand1);

        recipeCommand.setNotes(notesCommand);

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertEquals(RECIPE_ID,recipe.getId());
        assertEquals(URL,recipe.getUrl());
        assertEquals(SERVING,recipe.getServing());
        assertEquals(PREP_TIME,recipe.getPrepTime());
        assertEquals(DIRECTIONS,recipe.getDirections());
        assertEquals(DESCRIPTION,recipe.getDescription());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(NOTES_ID,recipe.getNotes().getId());
        assertEquals(2,recipe.getIngredients().size());
        assertEquals(2,recipe.getCategories().size());


    }

}