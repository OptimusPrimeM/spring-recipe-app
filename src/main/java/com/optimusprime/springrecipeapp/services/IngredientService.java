package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
