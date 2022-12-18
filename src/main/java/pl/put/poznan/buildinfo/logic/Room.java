package pl.put.poznan.buildinfo.logic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing a room, inheriting from the location class.
 *
 * @author Mikołaj Krakowiak, Jakub Kozłowksi, Adam Kopiec
 * @version 2.0
 */
public class Room extends Location {

    private double area;
    private double volume;
    private double light;
    private double heating;

    /**
     * Class constructor specifying the room features.
     *
     * @param id      room identifier
     * @param name    room name
     * @param area    room area
     * @param volume  room volume
     * @param light   room light power
     * @param heating room heating
     */
    @JsonCreator
    public Room(@JsonProperty("id") int id, @JsonProperty("name") String name,
                @JsonProperty("area") double area, @JsonProperty("volume") double volume,
                @JsonProperty("light") double light, @JsonProperty("heating") double heating
    ) {
        super(id, name);
        this.area = area;
        this.volume = volume;
        this.light = light;
        this.heating = heating;
    }

    /**
     * Method that sets room area.
     *
     * @param area room area
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * Method that sets room volume.
     *
     * @param volume room volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * Method that sets room light power.
     *
     * @param light light power
     */
    public void setLight(double light) {
        this.light = light;
    }

    /**
     * Method that sets room heating.
     *
     * @param heating room heating
     */
    public void setHeating(double heating) {
        this.heating = heating;
    }

    /**
     * Method that returns room area.
     *
     * @return area room area
     */
    public double getArea() {
        return area;
    }

    /**
     * Method that returns room light power.
     *
     * @return light room light power
     */
    public double getLight() {
        return light;
    }

    /**
     * Method that returns room volume.
     *
     * @return volume room volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Method that returns room heating.
     *
     * @return heating room heating
     */

    public double getHeating() {
        return heating;
    }


    /**
     * Method calculating the room area.
     *
     * @return area room area
     */
    @Override
    public double calculateArea() {
        return getArea();
    }

    /**
     * Method calculating the room volume.
     *
     * @return volume room volume
     */
    @Override
    public double calculateVolume() {
        return getVolume();
    }

    /**
     * Method calculating the room light power.
     *
     * @return lightPower room light power
     */
    @Override
    public double calculateLightPower() {
        return getLight() / getArea();
    }

    /**
     * Method calculating the room energy consumption for heating.
     *
     * @return energyHeating energy consumption for heating
     */
    @Override
    public double calculateHeatingUsage() {
        return getHeating() / getVolume();
    }

    /**
     * Method that returns null because the room has no nested elements, later used in the search for locations with a certain id.
     *
     * @return null
     */
    @Override
    public List<Location> getNestedList() {
        return null;
    }
}
