package org.zenika.zba.zbadata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Api(description = "Abstract class Step used to join steps to the recipe_step table")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Step implements Serializable {

    private long id;
    private int selectedStep;
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

    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
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
                ", recipe=" + recipeStep +
                '}';
    }
}
