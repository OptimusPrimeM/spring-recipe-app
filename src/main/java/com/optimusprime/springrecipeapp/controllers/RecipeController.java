package com.optimusprime.springrecipeapp.controllers;

import com.optimusprime.springrecipeapp.commands.RecipeCommand;
import com.optimusprime.springrecipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeForm";
    }

     @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
           RecipeCommand savedCommand= recipeService.saveRecipeCommand(command);
         return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        log.debug("Update id:" + id + " .in the controller class");
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeForm";
    }

    @GetMapping("/recipe/{id}/delete")
   public String deleteById(@PathVariable String id){

        log.debug("Deleting id:" + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
   }
}
