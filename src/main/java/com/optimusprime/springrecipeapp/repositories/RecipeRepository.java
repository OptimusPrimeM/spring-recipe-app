package com.optimusprime.springrecipeapp.repositories;

import com.optimusprime.springrecipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
