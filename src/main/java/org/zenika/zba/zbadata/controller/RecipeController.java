package org.zenika.zba.zbadata.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zenika.zba.zbadata.controller.recipe.Save;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.dao.RecipeStepDao;
import org.zenika.zba.zbadata.dao.StepDao;
import org.zenika.zba.zbadata.exception.RecipeNotFindException;
import org.zenika.zba.zbadata.exception.StepsNotFindException;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.RecipeStep;
import org.zenika.zba.zbadata.model.Step;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Receive the CRUD command")
@RestController
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private RecipeStepDao recipeStepDao;
    @Autowired
    private StepDao stepDao;

    @GetMapping(value = "/Recipe")
    public List<Recipe> listRecipe() throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findAll();
        if (recipe == null) throw new RecipeNotFindException("No recipe found");
        return recipe;
    }

    @GetMapping(value = "/Steps{id}")
    public List<Step> listSteps(@PathVariable long id) throws StepsNotFindException {
        List<RecipeStep> recipeSteps = recipeStepDao.findByRecipeId(id);
        List<Step> steps = new ArrayList();
        for (RecipeStep recipeStep: recipeSteps) {
            steps.add(recipeStep.getStep());
        }
        if (steps == null) throw new StepsNotFindException("No step found");
        return steps;
    }

    @DeleteMapping(value = "/Recipe{value}")
    public String deleteRecipe(@PathVariable String value) throws RecipeNotFindException {
        Recipe recipe = recipeDao.findByName(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe found for this name");
        recipeDao.delete(recipe);
        return "Deleted";
    }

    @PostMapping(value = "/Recipe")
    public Object addRecipe(@RequestBody Object object) {
        Save save = new Save();
        System.out.println("post");
        return save.saveFunction(object, recipeDao, recipeStepDao);
    }

    @PutMapping(value = "/Recipe")
    public long updateRecipe(@RequestBody Object object) {
        Save save = new Save();
        System.out.println("put");
        return save.saveFunction(object, recipeDao, recipeStepDao);
    }
}