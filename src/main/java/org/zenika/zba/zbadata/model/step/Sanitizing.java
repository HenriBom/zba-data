package org.zenika.zba.zbadata.model.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import org.zenika.zba.zbadata.model.Step;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Api(description = "Object contain the sanitizing description default id is foreign key to step")
@Entity
@PrimaryKeyJoinColumn(name = "sanitizingId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sanitizing extends Step {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
