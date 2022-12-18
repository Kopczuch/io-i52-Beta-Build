package pl.put.poznan.buildinfo.logic;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * An abstract class representing locations, whose methods are then used by inheriting classes: Building, Floor and Room.
 *
 * @author Mikołaj Krakowiak, Jakub Kozłowski, Adam Kopiec
 * @verion 2.0
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Room.class, name = "room"),
        @JsonSubTypes.Type(value = Floor.class, name = "floor"),
        @JsonSubTypes.Type(value = Building.class, name = "building")
})

public abstract class Location implements Serializable {

    private int id;
    private String name;

    /**
     * Basic constructor of location-type objects.
     *
     * @param id   location identifier
     * @param name location name
     */
    @JsonCreator
    public Location(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Method that sets the location id.
     *
     * @param id location identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method that returns the location id.
     *
     * @return id location identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Method that sets the location name.
     *
     * @param name location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that returns the location name.
     *
     * @return name location name
     */
    public String getName() {
        return name;
    }

    public abstract double calculateArea();

    public abstract double calculateVolume();

    public abstract double calculateLightPower();

    public abstract double calculateHeatingUsage();

    public abstract List<Location> getNestedList();
}
