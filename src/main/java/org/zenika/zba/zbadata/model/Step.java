package org.zenika.zba.zbadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Api(description = "Object Step used to transfer data beteween the Rest API and the database")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Step implements Serializable {

    private long id;
    private int selectedStep;
    private String description;
    private Set<RecipeStep> recipeStep;

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public int getSelectedStep() {
        return selectedStep;
    }

    public void setSelectedStep(int selectedStep) {
        this.selectedStep = selectedStep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<RecipeStep> getRecipe() {
        return recipeStep;
    }

    public void setRecipe(Set<RecipeStep> recipe) {
        this.recipeStep = recipe;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", selectedStep=" + selectedStep +
                ", description='" + description + '\'' +
                ", recipe=" + recipeStep +
                '}';
    }
}
