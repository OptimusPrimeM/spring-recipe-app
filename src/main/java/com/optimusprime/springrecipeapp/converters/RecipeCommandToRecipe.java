package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.RecipeCommand;
import com.optimusprime.springrecipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory, IngredientCommandToIngredient ingredientCommandToIngredient,
                                 NotesCommandToNotes notesCommandToNotes) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
            Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setCookTime(source.getCookTime());
            recipe.setDescription(source.getDescription());
            recipe.setDifficulty(source.getDifficulty());
            recipe.setDirections(source.getDirections());
            recipe.setPrepTime(source.getPrepTime());
            recipe.setServing(source.getServing());
            recipe.setUrl(source.getUrl());
            recipe.setSource(source.getSource());
            recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));

            if (source.getCategories() != null && source.getCategories().size() > 0) {
                source.getCategories()
                        .forEach(category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
            }

            if (source.getIngredients() != null && source.getIngredients().size() > 0) {
                source.getIngredients()
                        .forEach(ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
            }
            return recipe;
        }

    }

