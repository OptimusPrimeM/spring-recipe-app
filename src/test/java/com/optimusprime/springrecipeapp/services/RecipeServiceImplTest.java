package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.converters.RecipeCommandToRecipe;
import com.optimusprime.springrecipeapp.converters.RecipeToRecipeCommand;
import com.optimusprime.springrecipeapp.domain.Recipe;
import com.optimusprime.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository,recipeToRecipeCommand,recipeCommandToRecipe);
    }

    @Test
    public void getRecipe() throws Exception {

        Recipe recipe = new Recipe();
        HashSet recipeData =  new HashSet();
        recipeData.add(recipe);

        when(recipeService.getRecipe()).thenReturn(recipeData);
        Set<Recipe> recipes =recipeService.getRecipe();
          assertEquals(recipes.size(),1);
          verify(recipeRepository,times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
       Recipe recipe = new Recipe();
       recipe.setId(1L);
        Optional<Recipe>  recipeOptional =Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned",recipeReturned);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    public void testDeleteByID() throws Exception {

        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //no when , since method has void return type

        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}