package org.zenika.zba.zbadata.model;

import io.micrometer.core.lang.NonNull;
import io.swagger.annotations.Api;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Api(description = "Object Recipe used to transfer data between the Rest API and the database")
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    @NotNull
    private String name;
    @NonNull
    private String ingredientType;
    @NonNull
    private String malt;
    @NonNull
    private String creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public String getMalt() {
        return malt;
    }

    public void setMalt(String malt) {
        this.malt = malt;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredientType='" + ingredientType + '\'' +
                ", malt='" + malt + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
