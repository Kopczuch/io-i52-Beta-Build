package pl.put.poznan.buildinfo.logic;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Implementation of the abstract class Building
 *
 * @author Mikołaj Krakowiak
 * @version 1.0
 */


public class Building extends Location{

    private List<Floor> floors = new ArrayList<Floor>();
    private int id;
    private String name;

    @JsonCreator
    public Building(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("floors") List<Floor> floors){
        super(id, name);
        this.floors = floors;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor f){
        floors.add(f);
    }

    @Override
    public double calculateArea() {
        double sumArea = 0;
        for (Floor f: floors){
            sumArea += f.calculateArea();
        }
        return sumArea;
    }

    @Override
    public double calculateVolume() {
        double sumVolume = 0;
        for (Floor f: floors){
            sumVolume += f.calculateVolume();
        }
        return sumVolume;
    }

    @Override
    public double calculateLightPower() {
        double sumLightPower = 0;
        for (Floor f: floors){
            sumLightPower += f.calculateLightPower();
        }
        return sumLightPower;
    }
}
