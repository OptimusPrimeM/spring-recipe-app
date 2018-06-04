package com.optimusprime.springrecipeapp.controllers;

import com.optimusprime.springrecipeapp.domain.Category;
import com.optimusprime.springrecipeapp.domain.UnitOfMeasure;
import com.optimusprime.springrecipeapp.repositories.CategoryRepository;
import com.optimusprime.springrecipeapp.repositories.UnitOfMeasureRepository;
import com.optimusprime.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

      private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(Model model){
        log.debug("Loading Index page");
        model.addAttribute("recipes",recipeService.getRecipe());
        return "index";
    }
}
