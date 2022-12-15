package pl.put.poznan.buildinfo.logic;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Room.class, name = "room"),
    @JsonSubTypes.Type(value = Floor.class, name = "floor"),
    @JsonSubTypes.Type(value = Building.class, name = "building")
})
public abstract class Location implements Serializable {

    private int id;
    private String name;

    @JsonCreator
    public Location(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateArea();

    public abstract double calculateVolume();

    public abstract double calculateLightPower();

    public abstract double calculateHeatingUsage();

    public abstract List<Location> getNestedList();
}
