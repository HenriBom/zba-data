package org.zenika.zba.zbadata.model.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import org.zenika.zba.zbadata.model.Step;

import javax.persistence.*;

@Api(description = "Object Step used to transfer data beteween the Rest API and the database")
@Entity
@PrimaryKeyJoinColumn(name = "brewingId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brewing extends Step {

    private int timeH, timeMin, heat, water;
    private String hopperId;            // to map to hopper ingredient
    private String description;

    public int getTimeH() {
        return timeH;
    }

    public void setTimeH(int timeH) {
        this.timeH = timeH;
    }

    public int getTimeMin() {
        return timeMin;
    }

    public void setTimeMin(int timMin) {
        this.timeMin = timMin;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public String getHopperId() {
        return hopperId;
    }

    public void setHopperId(String hopperId) {
        this.hopperId = hopperId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
