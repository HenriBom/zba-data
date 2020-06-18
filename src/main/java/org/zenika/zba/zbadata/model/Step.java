package org.zenika.zba.zbadata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.Api;
import org.zenika.zba.zbadata.model.step.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Api(description = "Abstract class Step used to join steps to the recipe_step table")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bottling.class, name ="bottling"),
        @JsonSubTypes.Type(value = Brewing.class, name ="brewing"),
        @JsonSubTypes.Type(value = Colding.class, name ="colding"),
        @JsonSubTypes.Type(value = Crushing.class, name ="crushing"),
        @JsonSubTypes.Type(value = Density.class, name ="density"),
        @JsonSubTypes.Type(value = Fermenting.class, name ="fermenting"),
        @JsonSubTypes.Type(value = Filtering.class, name ="filtering"),
        @JsonSubTypes.Type(value = Hopping.class, name ="hopping"),
        @JsonSubTypes.Type(value = Leavening.class, name ="leavening"),
        @JsonSubTypes.Type(value = Sanitizing.class, name ="sanitizing")
})
public abstract class Step implements Serializable {

    private long id;
    private int selectedStep;

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

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", selectedStep=" + selectedStep +
                '}';
    }
}
