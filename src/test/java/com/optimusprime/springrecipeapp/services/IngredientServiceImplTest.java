package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.commands.IngredientCommand;
import com.optimusprime.springrecipeapp.converters.IngredientToIngredientCommand;
import com.optimusprime.springrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.optimusprime.springrecipeapp.domain.Ingredient;
import com.optimusprime.springrecipeapp.domain.Recipe;
import com.optimusprime.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    IngredientServiceImpl  ingredientService;

     @Mock
    RecipeRepository  recipeRepository;

    private final IngredientToIngredientCommand ingredientToIngredientCommand;



    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand =  new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand,recipeRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() throws Exception {
    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient  ingredient = new Ingredient();
        ingredient.setId(1L);

        Ingredient  ingredient1 = new Ingredient();
        ingredient.setId(2L);

        Ingredient  ingredient2 = new Ingredient();
        ingredient.setId(3L);

        recipe.addIngredients(ingredient);
        recipe.addIngredients(ingredient1);
        recipe.addIngredients(ingredient2);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand= ingredientService.findByRecipeIdAndIngredientId(1L,3L);

        //when
        assertEquals(Long.valueOf(3L),ingredientCommand.getId());
        assertEquals(Long.valueOf(1L),ingredientCommand.getRecipeId());
        verify(recipeRepository,times(1)).findById(anyLong());


    }
}