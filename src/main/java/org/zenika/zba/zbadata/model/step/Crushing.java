package org.zenika.zba.zbadata.model.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import org.zenika.zba.zbadata.model.Step;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Api(description = "Object Step used to transfer data beteween the Rest API and the database")
@Entity
@PrimaryKeyJoinColumn(name = "crushingId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crushing extends Step {

    private String description, malt;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMalt() {
        return malt;
    }

    public void setMalt(String malt) {
        this.malt = malt;
    }

    @Override
    public String toString() {
        return "Crushing{" +
                "description='" + description + '\'' +
                ", malt='" + malt + '\'' +
                '}';
    }
}
