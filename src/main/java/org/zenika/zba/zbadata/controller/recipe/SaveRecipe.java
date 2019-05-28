package org.zenika.zba.zbadata.controller.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.Step;
import org.zenika.zba.zbadata.model.step.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveRecipe {

    @Autowired
    private RecipeDao recipeDao;

    private ObjectMapper mapper = new ObjectMapper();
    private Recipe recipe;
    private JsonNode root;
    private String stepString = "", json = "";
    private long id = 0;

    // java object to json -> take recipe and step from the json -> map to model -> save to db
    public long mainSave(Object object) {

        try {
            json = mapper.writeValueAsString(object);                       // convert to json
            mapRecipe();
            addSteps();
            recipeDao.save(recipe);
            id = recipeDao.findByName(recipe.getName()).getId();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return id;
    }

    // map recipe
    private void mapRecipe() {

        String recipeString = "";

        try {
            root = mapper.readTree(json);
            recipeString = root.path("recipe").toString();                  // select from Json
            id = Long.parseLong(root.path("recipeId").toString());          // test if id != null
            recipe = mapper.readValue(recipeString, Recipe.class);   // map to model
            recipe.setId(id);                                               // add Id to recipe
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add new steps
    private void addSteps() {
        List<Step> steps = new ArrayList();
        JsonNode arrayNode = root.path("recipeSteps").path("steps");
        if (arrayNode.isArray()) {
            int i = 0;
            try {
                for (JsonNode objectNode : arrayNode) {                     // save each step
                    i++;
                    stepString = objectNode.toString();
                    switch (objectNode.path("selectedStep").textValue()) {
                        case "1":
                            Sanitizing sanitizing = mapper.readValue(stepString, Sanitizing.class);
                            sanitizing.setRecipe(recipe);
                            steps.add(sanitizing);
                            break;
                        case "2":
                            Crushing crushing = mapper.readValue(stepString, Crushing.class);
                            crushing.setRecipe(recipe);
                            steps.add(crushing);
                            break;
                        case "3":
                            Brewing brewing = mapper.readValue(stepString, Brewing.class);
                            brewing.setRecipe(recipe);
                            steps.add(brewing);
                            break;
                        case "4":
                            Filtering filtering = mapper.readValue(stepString, Filtering.class);
                            filtering.setRecipe(recipe);
                            steps.add(filtering);
                            break;
                        case "5":
                            Hopping hopping = mapper.readValue(stepString, Hopping.class);
                            hopping.setRecipe(recipe);
                            steps.add(hopping);
                            break;
                        case "6":
                            Colding colding = mapper.readValue(stepString, Colding.class);
                            colding.setRecipe(recipe);
                            steps.add(colding);
                            break;
                        case "7":
                            Leavening leavening = mapper.readValue(stepString, Leavening.class);
                            leavening.setRecipe(recipe);
                            steps.add(leavening);
                            break;
                        case "8":
                            Density density = mapper.readValue(stepString, Density.class);
                            density.setRecipe(recipe);
                            steps.add(density);
                            break;
                        case "9":
                            Fermenting fermenting = mapper.readValue(stepString, Fermenting.class);
                            fermenting.setRecipe(recipe);
                            steps.add(fermenting);
                            break;
                        case "10":
                            Bottling bottling = mapper.readValue(stepString, Bottling.class);
                            bottling.setRecipe(recipe);
                            steps.add(bottling);
                            break;
                        default:
                            System.out.println("SelectedStep error");
                    }
                    recipe.setStep(steps);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
