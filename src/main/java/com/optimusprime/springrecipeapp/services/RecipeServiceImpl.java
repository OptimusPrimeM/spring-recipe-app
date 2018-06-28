package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.commands.RecipeCommand;
import com.optimusprime.springrecipeapp.converters.RecipeCommandToRecipe;
import com.optimusprime.springrecipeapp.converters.RecipeToRecipeCommand;
import com.optimusprime.springrecipeapp.domain.Recipe;
import com.optimusprime.springrecipeapp.exceptions.NotFoundException;
import com.optimusprime.springrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements  RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipe() {

        log.debug("I'm in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if(!recipeOptional.isPresent()){
//             throw  new RuntimeException("Recipe not found");
             throw new NotFoundException("Recipe not found");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe saveRecipe =null;
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
         if (detachedRecipe!=null){
              saveRecipe =recipeRepository.save(detachedRecipe);
         }
        log.debug("Saved RecipeId: "+saveRecipe.getId());
        return recipeToRecipeCommand.convert(saveRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    public void deleteById(Long idToDelete) {
         recipeRepository.deleteById(idToDelete);
    }
}
