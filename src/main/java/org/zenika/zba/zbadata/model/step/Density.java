package org.zenika.zba.zbadata.model.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import org.zenika.zba.zbadata.model.Step;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Api(description = "Object Step used to transfer data beteween the Rest API and the database")
@Entity
@PrimaryKeyJoinColumn(name = "densityId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Density extends Step {

    private String description, density;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    @Override
    public String toString() {
        return "Density{" +
                "description='" + description + '\'' +
                ", density='" + density + '\'' +
                '}';
    }
}
