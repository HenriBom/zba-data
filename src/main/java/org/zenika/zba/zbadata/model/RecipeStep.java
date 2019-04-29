package org.zenika.zba.zbadata.model;

import io.swagger.annotations.Api;

import javax.persistence.*;
import java.io.Serializable;

@Api(description = "Object RecipeStep used to link Recipe and Step")
@Entity
@Table(name = "recipe_step")
public class RecipeStep implements Serializable {

    private long id;
    private Recipe recipe;
    private Step step;
    private int stepNumber;

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @ManyToOne
    @JoinColumn(name = "step_id")
    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    @Column(name = "step_number")
    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public String toString() {
        return "RecipeStep{" +
                "id=" + id +
                ", recipe=" + recipe +
                ", step=" + step +
                ", stepNumber=" + stepNumber +
                '}';
    }
}
