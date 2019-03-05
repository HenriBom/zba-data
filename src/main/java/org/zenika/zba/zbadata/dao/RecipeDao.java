package org.zenika.zba.zbadata.dao;

import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zenika.zba.zbadata.model.Recipe;

import java.util.List;

@Api(description = "Data Access Object for the user table")
@Repository
public interface RecipeDao extends JpaRepository<Recipe,Integer> {
    Recipe findById(int id);
    Recipe findByName(String name);
    List<Recipe> findByIngredientType(String ingredientType);
    List<Recipe> findByMalt(String malt);
    List<Recipe> findByCreator(String creator);
}
