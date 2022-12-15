package pl.put.poznan.buildinfo.logic;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Building extends Location {

    private List<Floor> floors = new ArrayList<Floor>();

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

    @Override
    public double calculateHeatingUsage()
    {
        double sumHeatingUsage = 0;
        for (Floor f : floors)
        {
            sumHeatingUsage += f.calculateHeatingUsage();
        }
        return sumHeatingUsage;
    }

    @Override 
    public List getNestedList() {
        return floors;
    }
}
