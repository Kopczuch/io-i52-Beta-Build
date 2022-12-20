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
 * @version 1.0
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Room.class, name = "room"),
        @JsonSubTypes.Type(value = Floor.class, name = "floor"),
        @JsonSubTypes.Type(value = Building.class, name = "building")
})

public abstract class Location implements Serializable {

    /**
     * Location unique identifier.
     */
    private int id;

    /**
     * Name of location.
     */
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

    /**
     * Abstract method for calculating location area.
     *
     * @return locationArea total location area
     */
    public abstract double calculateArea();

    /**
     * Abstract method for calculating location volume.
     *
     * @return locationVolume total location volume
     */
    public abstract double calculateVolume();

    /**
     * Abstract method for calculating light power.
     *
     * @return locationLightPower total location light power
     */
    public abstract double calculateLightPower();

    /**
     * Abstract method for calculating energy consumption for heating.
     *
     * @return locationHeatingUsage total location energy consumption for heating
     */
    public abstract double calculateHeatingUsage();

    /**
     * Abstract method enabling the return of floors for the building and rooms for the floors.
     *
     * @return nestedList list of floors or rooms, if used on room object, returns null
     */
    public abstract List<Location> getNestedList();
}
