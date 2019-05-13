package org.zenika.zba.zbadata.controller.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zenika.zba.zbadata.dao.RecipeDao;
import org.zenika.zba.zbadata.dao.RecipeStepDao;
import org.zenika.zba.zbadata.model.Recipe;
import org.zenika.zba.zbadata.model.RecipeStep;
import org.zenika.zba.zbadata.model.Step;
import org.zenika.zba.zbadata.model.step.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Save {

    // java object to json -> take recipe and step from the json -> map to model -> save to db
    public long saveFunction(Object object, RecipeDao recipeDao, RecipeStepDao recipeStepDao) {
        ObjectMapper mapper = new ObjectMapper();
        String json ="", recipeString ="", stepString="";
        List<Step> steps = new ArrayList();
        long id = 0;
        try {
            json = mapper.writeValueAsString(object);                       // convert to json
        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }
        try {

            // save recipe
            JsonNode root = mapper.readTree(json);
            recipeString = root.path("recipe").toString();                  // select from Json
            System.out.println(root.path("recipeId").toString());
            id = Long.parseLong(root.path("recipeId").toString());      // test if id != null
            Recipe recipe = mapper.readValue(recipeString, Recipe.class);   // map to model
            recipe.setId(id);                                               // add Id to recipe
            recipeDao.save(recipe);                                         // save to db

            // delete all steps
            List<RecipeStep> listRecipeStep = recipeStepDao.findAll();
            for(RecipeStep recipeStep : listRecipeStep) {
                if(recipe.getName().equals(recipeStep.getRecipe().getName())) {
                    recipeStepDao.delete(recipeStep);
                }
            }

            // add new steps
            JsonNode arrayNode = root.path("recipeSteps").path("steps");
            if (arrayNode.isArray()) {
                RecipeStep recipeStep = new RecipeStep();
                int i = 0;
                for (JsonNode objectNode : arrayNode) {                     // save each step
                    i++;
                    stepString = objectNode.toString();
                    switch (objectNode.path("selectedStep").textValue()){
                        case "1" :
                            Sanitizing sanitizing = mapper.readValue(stepString, Sanitizing.class);
                            steps.add(sanitizing);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(sanitizing);
                            break;
                        case "2" :
                            Crushing crushing = mapper.readValue(stepString, Crushing.class);
                            steps.add(crushing);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(crushing);
                            break;
                        case "3" :
                            Brewing brewing = mapper.readValue(stepString, Brewing.class);
                            steps.add(brewing);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(brewing);
                            break;
                        case "4" :
                            Filtering filtering = mapper.readValue(stepString, Filtering.class);
                            steps.add(filtering);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(filtering);
                            break;
                        case "5" :
                            Hopping hopping = mapper.readValue(stepString, Hopping.class);
                            steps.add(hopping);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(hopping);
                            break;
                        case "6" :
                            Colding colding = mapper.readValue(stepString, Colding.class);
                            steps.add(colding);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(colding);
                            break;
                        case "7" :
                            Leavening leavening = mapper.readValue(stepString, Leavening.class);
                            steps.add(leavening);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(leavening);
                            break;
                        case "8" :
                            Density density = mapper.readValue(stepString, Density.class);
                            steps.add(density);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(density);
                            break;
                        case "9" :
                            Fermenting fermenting = mapper.readValue(stepString, Fermenting.class);
                            steps.add(fermenting);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(fermenting);
                            break;
                        case "10" :
                            Bottling bottling = mapper.readValue(stepString, Bottling.class);
                            steps.add(bottling);
                            recipeStep.setRecipe(recipe);
                            recipeStep.setStep(bottling);
                            break;
                        default:
                                System.out.println("SelectedStep error");
                    }
                    recipeStep.setStepNumber(i);
                    recipeStepDao.save(recipeStep);
                }
            }
            id = recipeDao.findByName(recipe.getName()).getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
}
