package org.zenika.zba.zbadata.model.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import org.zenika.zba.zbadata.model.Step;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Api(description = "Object Step used to transfer data beteween the Rest API and the database")
@Entity
@PrimaryKeyJoinColumn(name = "filteringId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filtering extends Step {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Filtering{" +
                "description='" + description + '\'' +
                '}';
    }
}
