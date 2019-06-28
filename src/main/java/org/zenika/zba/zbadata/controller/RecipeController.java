package org.zenika.zba.zbadata.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.dao.StepDao;
import org.zenika.zba.zbadata.exception.RecipeNotFindException;
import org.zenika.zba.zbadata.exception.StepsNotFoundException;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.Step;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Receive the CRUD command")
@RestController
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private StepDao stepDao;

    @GetMapping(value = "/recipes")
    public ResponseEntity<List<Recipe>> listRecipe() throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findAll();
        if (recipe == null) throw new RecipeNotFindException("No recipe found");
        return ok(recipe);
    }

    @GetMapping(value = "/recipe/{id}/steps")
    public ResponseEntity<List<Step>> listSteps(@PathVariable long id) throws StepsNotFoundException {
        Recipe recipe = recipeDao.findById(id);
        List<Step> steps = recipe.getSteps();
        return ok(steps);
    }

    @DeleteMapping(value = "recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String id) throws RecipeNotFindException {
        Recipe recipe = recipeDao.findByName(id);
        if (recipe == null) throw new RecipeNotFindException("No recipe found for this name");
        recipeDao.delete(recipe);
        return ok("Deleted");
    }

    @PostMapping(value = "/recipe")
    public ResponseEntity<Object> addRecipe(@RequestBody String json) {
        if (json == null) throw new NullPointerException("No body");
        try {
            Recipe recipe = new Recipe();
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);
                recipe = mapper.readValue(root.toString(), Recipe.class);          // map to model
                recipeDao.save(recipe);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.created(new URI("db_zab_database")).body(recipe.getId());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(json);
        }
    }

    @PutMapping(value = "/recipe/{id}")
    public ResponseEntity<Object> updateRecipe(@PathVariable long id, @RequestBody String json) {
        if (json == null) throw new NullPointerException("No body");
        try {
            Recipe recipe;
            if(recipeDao.findById(id) != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);
                recipe = mapper.readValue(root.toString(), Recipe.class);          // map to model
                recipeDao.save(recipe);
                return ResponseEntity.accepted().body(recipe);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(json);
        }
    }
}