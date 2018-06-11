package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.commands.RecipeCommand;
import com.optimusprime.springrecipeapp.converters.RecipeCommandToRecipe;
import com.optimusprime.springrecipeapp.converters.RecipeToRecipeCommand;
import com.optimusprime.springrecipeapp.domain.Recipe;
import com.optimusprime.springrecipeapp.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServicelT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

   @Transactional
    @Test
    public void testSaveOfDecription() throws Exception {

       //given
       Iterable<Recipe> recipes= recipeRepository.findAll();
       Recipe testRecipe = recipes.iterator().next();
       RecipeCommand testRecipeCommand=  recipeToRecipeCommand.convert(testRecipe);

       //when
       testRecipeCommand.setDescription(NEW_DESCRIPTION);
       RecipeCommand savedRecipeCommand=recipeService.saveRecipeCommand(testRecipeCommand);

       //then
       assertEquals(NEW_DESCRIPTION,savedRecipeCommand.getDescription());
       assertEquals(testRecipe.getId(),savedRecipeCommand.getId());
       assertEquals(testRecipe.getCategories().size(),savedRecipeCommand.getCategories().size());
       assertEquals(testRecipe.getIngredients().size(),savedRecipeCommand.getIngredients().size());
    }
}