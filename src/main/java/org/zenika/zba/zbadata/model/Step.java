package org.zenika.zba.zbadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Api(description = "Abstract class Step used to join steps to the recipe_step table")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Step implements Serializable {

    private long id;
    private int selectedStep;
    private Recipe recipe;

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

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", selectedStep=" + selectedStep +
                ", recipe=" + recipe +
                '}';
    }
}
