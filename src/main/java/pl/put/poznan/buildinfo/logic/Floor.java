package pl.put.poznan.buildinfo.logic;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing a floor, inheriting from the location class.
 *
 * @author Mikołaj Krakowiak, Jakub Kozłowksi, Adam Kopiec
 * @version 1.0
 */
public class Floor extends Location {

    /**
     * List of floors located in the building.
     */
    private List<Room> rooms = new ArrayList<Room>();

    /**
     * Class constructor specifying the identifier and name of the floor as well as the rooms on the floor.
     *
     * @param id    floor identifier
     * @param name  floor name
     * @param rooms rooms located on the floor
     */
    @JsonCreator
    public Floor(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("rooms") List<Room> rooms) {
        super(id, name);
        this.rooms = rooms;
    }

    /**
     * Method that returns the rooms on a current floor.
     *
     * @return rooms
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Method for adding new rooms to a current floor.
     *
     * @param r new room
     */
    public void addRoom(Room r) {
        rooms.add(r);
    }

    /**
     * Method returning the sum of the floor surface area.
     * Summing up involves calculating the sum of the areas of all the rooms on current floor.
     *
     * @return sumArea total floor area
     */
    @Override
    public double calculateArea() {
        double areaSum = 0;
        for (Room r : rooms) {
            areaSum += r.getArea();
        }
        return areaSum;
    }

    /**
     * Method returning the sum of the floor volume.
     * Summing up involves calculating the volume of all the rooms on current floor.
     *
     * @return sumVolume total volume of the floor
     */
    @Override
    public double calculateVolume() {
        double volumeSum = 0;
        for (Room r : rooms) {
            volumeSum += r.getVolume();
        }
        return volumeSum;
    }

    /**
     * Method returning the sum of the floor light power.
     * Summing up involves calculating the light power of all the rooms on current floor.
     *
     * @return sumLightPower total light power of the floor
     */
    @Override
    public double calculateLightPower() {
        double sumLightPower = 0;
        for (Room r : rooms) {
            sumLightPower += r.calculateLightPower();
        }
        return sumLightPower;
    }

    /**
     * Method returning the floor sum of energy consumption for heating.
     * Summing up involves calculating the heating of all the rooms on current floor.
     *
     * @return sumHeatingUsage energy consumption for heating of the floor
     */
    @Override
    public double calculateHeatingUsage() {
        double sumHeatingUsage = 0;
        for (Room r : rooms) {
            sumHeatingUsage += r.calculateHeatingUsage();
        }
        return sumHeatingUsage;
    }

    /**
     * Method that returns the rooms of a current floor, later used in the search for locations with a certain id.
     *
     * @return rooms
     */
    @Override
    public List getNestedList() {
        return getRooms();
    }
}
