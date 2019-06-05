package org.zenika.zba.zbadata.dao;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zenika.zba.zbadata.model.Recipe;

import java.util.List;

@SwaggerDefinition(tags = { @Tag(name = "Tag1", description = "Data Access Object for the recipe table") })
@Api(tags = {"Tag1"})
@Repository
public interface RecipeDao extends JpaRepository<Recipe,Integer> {
    Recipe findById(Long id);
    Recipe findByName(String name);
    List<Recipe> findAll();
    List<Recipe> findByIngredientType(String ingredientType);
    List<Recipe> findByMalt(String malt);
    List<Recipe> findByCreator(String creator);
}
