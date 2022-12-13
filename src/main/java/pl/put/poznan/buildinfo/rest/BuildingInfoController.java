package pl.put.poznan.buildinfo.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

import pl.put.poznan.buildinfo.logic.*;

import java.util.List;

@RestController
public class BuildingInfoController {
    
    static ObjectMapper objectMapper = new ObjectMapper();

    // static TypeReference<List<Building>> typeReferenceBuilding = new TypeReference<List<Building>>() {};
    // static TypeReference<List<Floor>> typeReferenceFloor = new TypeReference<List<Floor>>() {};
    // static TypeReference<List<Room>> typeReferenceRoom = new TypeReference<List<Room>>() {};
    static TypeReference<List<Location>> typeReferenceLocation = new TypeReference<List<Location>>() {};


    // static TypeReference<Location> typeReferenceLocation2 = new TypeReference<Location>() {};
    
    // @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/ListOfBuildings")
    // public static List<Building> getListOfBuildings() throws IOException {
    //     List<Building> listaBudynkow = objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceBuilding);
    //     return listaBudynkow;
    // }

    // @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/building-{id}")
    // public static Building getBuilding(@PathVariable("id") int x) throws IOException {
    //     Building budynek = getListOfBuildings().stream().filter(_building -> _building.getId() == x).findFirst().orElse(null);
    //     return budynek;
    // }

    // @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/building-{id}-floors")
    // public static List<Floor> getListOfFloors(@PathVariable("id") int x) throws IOException {
    //     List<Floor> listaPieter = getBuilding(x).getFloors();
    //     return listaPieter;
    // }


    // ########## LOCATIONS ##########
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/ListOfLocations")
    public static List<Location> getListOfLocations() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceLocation);
    }

    public static Location findById(List<Location> list, int id) {
        for (Location o : list) {
            if (o.getId() == id) {
                System.out.println(o);
                return o;
            }
            if (o.getNestedList() != null) {
                List<Location> _o = o.getNestedList();
                Location result = findById(_o, id);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value="/show-{id}")
    public static Location getLocation(@PathVariable("id") int x) throws IOException {
        return findById(getListOfLocations(), x);
    }

    // ########## CALCULATIONS ##########

    // Function returning AREA of the location with given id
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value="/get_area/{id}")
    public static double getArea(@PathVariable("id") int x) throws IOException {
        return findById(getListOfLocations(), x).calculateArea();
    }

    // Function returning VOLUME of the location with given id
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_volume/{id}")
    public static double getVolume(@PathVariable("id") int x) throws IOException
    {
        return findById(getListOfLocations(), x).calculateVolume();
    }

    // Function returning LIGHT POWER of the location with given id
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_light_power/{id}")
    public static double getLightPower(@PathVariable("id") int x) throws IOException
    {
        return findById(getListOfLocations(), x).calculateLightPower();
    }


}
