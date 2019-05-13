package org.zenika.zba.zbadata.dao;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zenika.zba.zbadata.model.RecipeStep;

import java.util.List;

@SwaggerDefinition(tags = { @Tag(name = "Tag1", description = "Data Access Object for the join table between recipe and steps") })
@Api(tags = {"Tag1"})
@Repository
public interface RecipeStepDao extends JpaRepository<RecipeStep,Integer> {
    List<RecipeStep> findByRecipeId(long id);
}

