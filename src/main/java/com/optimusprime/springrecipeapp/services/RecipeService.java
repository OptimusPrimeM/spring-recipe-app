package com.optimusprime.springrecipeapp.services;

import com.optimusprime.springrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService  {
    Set<Recipe> getRecipe();
    Recipe findById(Long l);
}
