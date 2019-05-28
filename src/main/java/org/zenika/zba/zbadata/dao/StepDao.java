package org.zenika.zba.zbadata.dao;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zenika.zba.zbadata.model.Step;

import java.util.List;

@SwaggerDefinition(tags = { @Tag(name = "Tag1", description = "Data Access Object for the steps table") })
@Api(tags = {"Tag1"})
@Repository
public interface StepDao extends JpaRepository<Step,Integer> {
    Step findById(int id);
    List<Step> findBySelectedStep(int type);
    List<Step> findByRecipeId(long id);
}
