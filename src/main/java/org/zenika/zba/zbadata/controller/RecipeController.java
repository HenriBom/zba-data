package org.zenika.zba.zbadata.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zenika.zba.zbadata.controller.recipe.SaveRecipe;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.dao.RecipeStepDao;
import org.zenika.zba.zbadata.dao.StepDao;
import org.zenika.zba.zbadata.exception.RecipeNotFindException;
import org.zenika.zba.zbadata.exception.StepsNotFindException;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.Step;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

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
    public ResponseEntity<List<Recipe>> listRecipe() throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findAll();
        if (recipe == null) throw new RecipeNotFindException("No recipe found");
        return ok(recipe);
    }

    @GetMapping(value = "/Steps{id}")
    public ResponseEntity<List<Step>> listSteps(@PathVariable long id) throws StepsNotFindException {
        List<Step> steps = stepDao.findByRecipeId(id);
        if (steps.isEmpty()) throw new StepsNotFindException("No step found");
        return ok(steps);
    }

    @DeleteMapping(value = "/Recipe{value}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String value) throws RecipeNotFindException {
        Recipe recipe = recipeDao.findByName(value);
        if (recipe == null) throw new RecipeNotFindException("No recipe found for this name");
        recipeDao.delete(recipe);
        return ok("Deleted");
    }

    @PostMapping(value = "/Recipe")
    public ResponseEntity<Object> addRecipe(@RequestBody Object object) {
        SaveRecipe save = new SaveRecipe();
        if (object == null) throw new NullPointerException("No body");
        return ok(save.mainSave(object, recipeDao, stepDao));
    }

    @PutMapping(value = "/Recipe")
    public long updateRecipe(@RequestBody Object object) {
        SaveRecipe save = new SaveRecipe();
        if (object == null) throw new NullPointerException("No body");
        return save.mainSave(object, recipeDao, stepDao);
    }
}