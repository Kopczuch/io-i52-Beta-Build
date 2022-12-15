package pl.put.poznan.buildinfo.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import pl.put.poznan.buildinfo.logic.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class BuildingInfoController {
    
    static ObjectMapper objectMapper = new ObjectMapper();

     static TypeReference<List<Building>> typeReferenceBuilding = new TypeReference<List<Building>>() {};
    // static TypeReference<List<Floor>> typeReferenceFloor = new TypeReference<List<Floor>>() {};
    static TypeReference<List<Room>> typeReferenceRoom = new TypeReference<List<Room>>() {};
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

    //Root
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/")
    public String root()
    {
        String rootMessage = "Witaj!\n\n" +
                            "/ListOfLocations           -> Wyswietla liste wszystkich lokacji\n" +
                            "/show?id=<id>              -> Wyswietla dane lokacji o podanym id\n" +
                            "/get_area?id=<id>          -> Wyswietla powierzchnie lokacji o podanym id\n" +
                            "/get_volume?id=<id>        -> Wyswietla objetosc lokacji o podanym id\n" +
                            "/get_light_power?id=<id>   -> Wyswietla moc oswietlenia lokacji o podanym id\n";
        return rootMessage;
    }

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

//    Sad attempt at creating function checking if room heating usage is over the limit
//    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/heating")
//    public static List<Room> HeatingOverdose(@RequestParam("limit") double limit) throws IOException
//    {
//        List<Room> listRooms = objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceRoom);
//        List<Room> Overdose = new ArrayList<>();
//        for (Room r : listRooms)
//        {
//            if (r.calculateHeatingUsage() > limit)
//            {
//                Overdose.add(r);
//            }
//        }
//        return Overdose;
//    }


    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value="/show")
    public static Location getLocation(@RequestParam("id") int id) throws IOException
    {
        return findById(getListOfLocations(), id);
    }

    // ########## CALCULATIONS ##########

    // Function returning AREA of the location with given id
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value="/get_area")
    public static String getArea(@RequestParam("id") String idString) throws IOException
    {
        try
        {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateArea();
            return "Powierzchnia lokacji o id = " + id + " to:\n" + x;
        }
        catch (java.lang.NullPointerException e)
        {
            return "Brak lokacji o podanym id";
        }
        catch (java.lang.NumberFormatException e)
        {
            return "Id musi być liczbą";
        }

    }

    // Function returning VOLUME of the location with given id
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_volume")
    public static String getVolume(@RequestParam("id") String idString) throws IOException
    {
        try
        {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateVolume();
            return "Objetosc lokacji o id = " + id + " to:\n" + x;
        }
        catch (java.lang.NullPointerException e)
        {
            return "Brak lokacji o podanym id";
        }
        catch (java.lang.NumberFormatException e)
        {
            return "Id musi być liczbą";
        }
    }

    // Function returning LIGHT POWER of the location with given id
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_light_power")
    public static String getLightPower(@RequestParam("id") String idString) throws IOException
    {
        try
        {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateLightPower();
            return "Moc oswietlenia w przeliczeniu na jednostke powierzchni lokacji o id = " + id + " to:\n" + x;
        }
        catch (java.lang.NullPointerException e)
        {
            return "Brak lokacji o podanym id";
        }
        catch (java.lang.NumberFormatException e)
        {
            return "Id musi być liczbą";
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_heating_usage")
    public static String getHeatingUsage(@RequestParam("id") String idString) throws IOException
    {
        try
        {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateHeatingUsage();
            return "Zuzycie energii na ogrzewanie w przeliczeniu na jednostke objetosci lokacji o id = " + id + " to:\n" + x;
        }
        catch (java.lang.NullPointerException e)
        {
            return "Brak lokacji o podanym id";
        }
        catch (java.lang.NumberFormatException e)
        {
            return "Id musi być liczbą";
        }
    }

}
