package pl.put.poznan.buildinfo.logic;

/*
 * Implementation of the abstract class Room
 *
 * @author Mikołaj Krakowiak
 * @version 1.0
 */


public class Room extends Location{

    private double id;
    private String name;
    private double area;
    private double volume;
    private double light;

    private double heating;

    public Room(int id, String name){
        super(id, name);
    }

    public Room(int id, String name, double area, double volume, double light, double heating){
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
}
