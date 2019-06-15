package org.zenika.zba.zbadata.model.step;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import org.zenika.zba.zbadata.model.Step;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Api(description = "Object Step used to transfer data beteween the Rest API and the database")
@Entity
@PrimaryKeyJoinColumn(name = "fermentingId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fermenting extends Step {

    private int day;
    private int temperature;
    private int pressure;
    private String description;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Fermenting{" +
                "day=" + day +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", description='" + description + '\'' +
                '}';
    }
}
