package pl.put.poznan.buildinfo.logic;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;




public class Floor extends Location {

    private List<Room> rooms = new ArrayList<Room>();

    @JsonCreator
    public Floor(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("rooms") List<Room> rooms) {
        super(id, name);
        this.rooms = rooms;
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

    @Override
    public  double calculateHeatingUsage()
    {
        double sumHeatingUsage = 0;
        for (Room r : rooms)
        {
            sumHeatingUsage += r.calculateHeatingUsage();
        }
        return sumHeatingUsage;
    }

    @Override 
    public List getNestedList() {
        return rooms;
    }
}
