package pl.put.poznan.buildinfo.logic;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing a building, inheriting from the location class.
 *
 * @author Mikołaj Krakowiak, Jakub Kozłowksi, Adam Kopiec
 * @version 1.0
 */
public class Building extends Location {

    /**
     * List of floors located in the building
     */
    private List<Floor> floors = new ArrayList<Floor>();

    /**
     * Class constructor specifying the identifier and the name of the building as well as the floors in the building.
     *
     * @param id     building identifier
     * @param name   building name
     * @param floors floors located in the building
     */
    @JsonCreator
    public Building(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("floors") List<Floor> floors) {
        super(id, name);
        this.floors = floors;
    }

    /**
     * Method that returns the floors located in a building.
     *
     * @return floors
     */
    public List<Floor> getFloors() {
        return floors;
    }

    /**
     * Method for adding new floors to a building.
     *
     * @param f new floor
     */
    public void addFloor(Floor f) {
        floors.add(f);
    }


    /**
     * Method returning the sum of the building surface area.
     * Summing up involves calculating the sum of the areas of all the floors.
     *
     * @return sumArea total building area
     */
    @Override
    public double calculateArea() {
        double sumArea = 0;
        for (Floor f : floors) {
            sumArea += f.calculateArea();
        }
        return sumArea;
    }

    /**
     * Method returning the sum of the building volume.
     * Summing up involves calculating the volume of all the floors.
     *
     * @return sumVolume total volume of the building
     */
    @Override
    public double calculateVolume() {
        double sumVolume = 0;
        for (Floor f : floors) {
            sumVolume += f.calculateVolume();
        }
        return sumVolume;
    }

    /**
     * Method returning the sum of the building light power.
     * Summing up involves calculating the light power of all the floors.
     *
     * @return sumLightPower total light power of the building
     */
    @Override
    public double calculateLightPower() {
        double sumLightPower = 0;
        for (Floor f : floors) {
            sumLightPower += f.calculateLightPower();
        }
        return sumLightPower;
    }

    /**
     * Method returning the building sum of energy consumption for heating.
     * Summing up involves calculating the heating of all the floors.
     *
     * @return sumHeatingUsage energy consumption for heating of the building
     */
    @Override
    public double calculateHeatingUsage() {
        double sumHeatingUsage = 0;
        for (Floor f : floors) {
            sumHeatingUsage += f.calculateHeatingUsage();
        }
        return sumHeatingUsage;
    }

    /**
     * Method that returns the floors of a building, later used in the search for locations with a certain id.
     *
     * @return floors
     */
    @Override
    public List getNestedList() {
        return getFloors();
    }
}
