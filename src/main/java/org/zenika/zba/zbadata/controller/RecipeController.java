package org.zenika.zba.zbadata.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.exception.RecipeNotFindException;
import org.zenika.zba.zbadata.model.Recipe;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Receive the CRUD command")
@RestController
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;

    @GetMapping(value = "/Recipe")
    public List<Recipe> listRecipe() throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findAll();
        if (recipe == null) throw new RecipeNotFindException("No recipe find");
        return recipe;
    }

    @GetMapping(value = "/Recipe/id{value}")
    public Recipe findRecipeId(@PathVariable int value) throws RecipeNotFindException {
        Recipe recipe = recipeDao.findById(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe find for this id");
        return recipe;
    }

    @GetMapping(value = "/Recipe/name{value}")
    public Recipe findRecipeName(@PathVariable String value) throws RecipeNotFindException {
        Recipe recipe = recipeDao.findByName(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe find for this name");
        return recipe;
    }

    @GetMapping(value = "/Recipe/ingredientType{value}")
    public List<Recipe> findRecipeIngredientType(@PathVariable String value) throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findByIngredientType(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe find");
        return recipe;
    }

    @GetMapping(value = "/Recipe/malt{value}")
    public List<Recipe> findRecipeMalt(@PathVariable String value) throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findByMalt(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe find");
        return recipe;
    }

    @GetMapping(value = "/Recipe/userId{value}")
    public List<Recipe> findRecipeCreator(@PathVariable String value) throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findByCreator(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe find");
        return recipe;
    }

    @DeleteMapping(value = "/Recipe{value}")
    public String deleteRecipe(@PathVariable String value) throws RecipeNotFindException {
        Recipe recipe = recipeDao.findByName(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe find for this name");
        recipeDao.delete(recipe);
        return "Deleted";
    }

    @PostMapping(value = "/Recipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        System.out.println("post");
        recipeDao.save(recipe);
        return recipe;
    }

    @PutMapping(value = "/Recipe")
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        System.out.println("put");
        recipeDao.save(recipe);
        return recipe;
    }
}

