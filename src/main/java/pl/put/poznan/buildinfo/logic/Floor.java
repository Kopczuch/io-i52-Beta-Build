package pl.put.poznan.buildinfo.logic;

import java.util.ArrayList;
import java.util.List;

/*
 * Implementation of the abstract class Floor
 *
 * @author Mikołaj Krakowiak
 * @version 1.0
 */

public class Floor extends Interior {

    private List<Room> rooms = new ArrayList<Room>();
    private int id;
    private String name;


    public Floor(int id, String name){
        super(id, name);
    }

    public List<Room> getRooms(){
        return rooms;
    }

    public void addRoom(Room r){
        rooms.add(r);
    }

    @Override
    public double calculateArea(){
        double areaSum = 0;
        for (Room r: rooms){
            areaSum += r.getArea();
        }
        return areaSum;
    }

    @Override
    public double calculateVolume(){
        double volumeSum = 0;
        for (Room r: rooms){
            volumeSum += r.getVolume();
        }
        return volumeSum;
    }

    @Override
    public double calculateLightPower() {
        double sumLightPower = 0;
        for (Room r: rooms){
            sumLightPower += r.calculateLightPower();
        }
        return sumLightPower;
    }
}
