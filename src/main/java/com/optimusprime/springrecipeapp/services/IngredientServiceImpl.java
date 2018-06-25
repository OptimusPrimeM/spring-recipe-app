package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.commands.IngredientCommand;
import com.optimusprime.springrecipeapp.converters.IngredientCommandToIngredient;
import com.optimusprime.springrecipeapp.converters.IngredientToIngredientCommand;
import com.optimusprime.springrecipeapp.domain.Ingredient;
import com.optimusprime.springrecipeapp.domain.Recipe;
import com.optimusprime.springrecipeapp.repositories.RecipeRepository;
import com.optimusprime.springrecipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {


        log.debug("In the function of findByRecipeIdAndIngredientId ");

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            //todo impl error handling
            log.error("recipe " + recipeId + " not found ");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            //todo impl error handling
            log.error("Ingredient " + ingredientId + " not found ");
        }

        return ingredientCommandOptional.get();
    }


    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        log.debug("I'm in the Ingredient save service");
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            //todo todd error if not found
            log.error("Recipe not found for id:" + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.
                    getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo  address this
            } else {
                //add new ingredient
//                recipe.addIngredients(ingredientCommandToIngredient.convert(command));
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredients(ingredient);
            }
            Recipe savedRecipe = recipeRepository.save(recipe);

            //to do check for fall
            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());


        }

    }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {

        log.debug("Deleting ingredient:"+ recipeId + ":"+ idToDelete);
             Optional<Recipe> recipeOptional =  recipeRepository.findById(recipeId);

             if(recipeOptional.isPresent()){
                 Recipe recipe=recipeOptional.get();
                 log.debug("Found recipe");

                Optional<Ingredient> ingredientOptional= recipe.getIngredients()
                         .stream()
                         .filter(ingredient -> ingredient.getId().equals(idToDelete))
                         .findFirst();

                if(ingredientOptional.isPresent()){
                    log.debug("Found ingredient");
                    Ingredient ingredientToDelete =ingredientOptional.get();
                    ingredientToDelete.setRecipe(null);
                    recipe.getIngredients().remove(ingredientOptional.get());
                    recipeRepository.save(recipe);

                }
             }else{
                 log.debug("Recipe id "+recipeId+" not found");
             }

    }
}
