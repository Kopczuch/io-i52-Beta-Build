package pl.put.poznan.buildinfo.logic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Room extends Location{

    private double area;
    private double volume;
    private double light;
    private double heating;

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

    public void setArea(double area) {
        this.area = area;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public void setHeating(double heating) {
        this.heating = heating;
    }

    public double getArea() {
        return area;
    }

    public double getLight() {
        return light;
    }

    public double getVolume() {
        return volume;
    }

    public double getHeating() {
        return heating;
    }

    @Override
    public double calculateArea() {
       return getArea();
    }

    @Override
    public double calculateVolume() {
        return getVolume();
    }

    @Override
    public double calculateLightPower() {
        return light/area;
    }

    @Override
    public double calculateHeatingUsage() {
        return heating/volume;
    }

    @Override 
    public List<Location> getNestedList() {
        return null;
    }
}
