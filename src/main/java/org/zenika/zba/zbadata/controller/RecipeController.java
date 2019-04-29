/*package org.zenika.zba.zbadata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.dao.RecipeStepDao;
import org.zenika.zba.zbadata.dao.StepDao;
import org.zenika.zba.zbadata.exception.RecipeNotFindException;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.Step;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Api(description = "Receive the CRUD command")
@RestController
public class RecipeController {

    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private StepDao stepDao;
    @Autowired
    private RecipeStepDao recipeStepDao;

    @GetMapping(value = "/Recipe/test")
    public String testRecipe() {
        return "Ok";
    }

    @GetMapping(value = "/Recipe")
    public List<Recipe> listRecipe() throws RecipeNotFindException {
        List<Recipe> recipe = recipeDao.findAll();
        if (recipe == null) throw new RecipeNotFindException("No recipe found");
        return recipe;
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
        System.out.println("post");
        return temporaryFunction(object);
    }

    @PutMapping(value = "/Recipe")
    public Object updateRecipe(@RequestBody Object object) {
        System.out.println("put");
        return temporaryFunction(object);
    }

    // java object to json -> take recipe and step from the json -> map to model -> save to db
    Object temporaryFunction(Object object) {ObjectMapper mapper = new ObjectMapper();
        String json ="", recipeString ="", stepString="";
        try {
            json = mapper.writeValueAsString(object);                       // convert to json
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            JsonNode root = mapper.readTree(json);
            recipeString = root.path("recipe").toString();                  // select from Json
            Recipe recipe = mapper.readValue(recipeString, Recipe.class);   // map to model
            recipeDao.save(recipe);                                         // save to db

            JsonNode arrayNode = root.path("recipeSteps").path("steps");
            if (arrayNode.isArray()) {
                for (final JsonNode objectNode : arrayNode) {
                    stepString = objectNode.toString();
                    Step steps = mapper.readValue(stepString, Step.class);
                    stepDao.save(steps);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}

*/