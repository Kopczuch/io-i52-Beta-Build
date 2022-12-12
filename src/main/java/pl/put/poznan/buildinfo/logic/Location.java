package pl.put.poznan.buildinfo.logic;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
* Implementation of the abstract class Location
*
* @author Mikołaj Krakowiak
* @version 1.0
 */

public abstract class Location {

    private int id;
    private String name;

    public Location(int id, String name) {
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
}
