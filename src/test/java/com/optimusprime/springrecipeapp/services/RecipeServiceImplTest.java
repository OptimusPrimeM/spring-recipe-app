package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.domain.Recipe;
import com.optimusprime.springrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
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

}