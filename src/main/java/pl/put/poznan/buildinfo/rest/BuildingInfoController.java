package pl.put.poznan.buildinfo.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

    // Returns list of buildings
     @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/ListOfBuildings")
     public static List<Building> getListOfBuildings() throws IOException {
         List<Building> listaBudynkow = objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceBuilding);
         return listaBudynkow;
     }

     // Returns building with given id
     @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/building-{id}")
     public static Building getBuilding(@PathVariable("id") int x) throws IOException {
         return getListOfBuildings().stream().filter(_building -> _building.getId() == x).findFirst().orElse(null);
     }

    // @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/building-{id}-floors")
    // public static List<Floor> getListOfFloors(@PathVariable("id") int x) throws IOException {
    //     List<Floor> listaPieter = getBuilding(x).getFloors();
    //     return listaPieter;
    // }


    // ########## LOCATIONS ##########

    // Root
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/")
    public String root()
    {
        String rootMessage =
                "Witaj!\n\n" +
                "/ListOfLocations                           -> Wyswietla liste wszystkich lokacji\n" +
                "/show?id=<id>                              -> Wyswietla dane lokacji o podanym id\n" +
                "/get_area?id=<id>                          -> Wyswietla powierzchnie lokacji o podanym id\n" +
                "/get_volume?id=<id>                        -> Wyswietla objetosc lokacji o podanym id\n" +
                "/get_light_power?id=<id>                   -> Wyswietla moc oswietlenia lokacji o podanym id\n" +
                "/get_heating_usage?id=<id>                 -> Wyswietla zuzycie energii na ogrzewanie lokacji o podanym id\n" +
                "/heating_over_limit?limit=<limit>&id=<id>  -> Wyswietla pomieszczenia ktore przekraczaja okreslony limit zuzycia energii na ogrzewanie w budynku o podanym id";
        return rootMessage;
    }

    // Returns list of locations
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/ListOfLocations")
    public static List<Location> getListOfLocations() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceLocation);
    }

    // Returns location with given id
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

    // Returns buildings with heating usage over given limit
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/heating_over_limit")
    public static List<Room> HeatingOverdose(@RequestParam("limit") double limit, @RequestParam("id") int id) throws IOException
    {
        Building building = getBuilding(id);
        List<Room> Overdose = new ArrayList<>();
        for (Floor f : building.getFloors())
        {
            for (Room r : f.getRooms())
            {
                if (r.calculateHeatingUsage() > limit)
                {
                    Overdose.add(r);
                }
            }
        }
        return Overdose;
    }

    // Returns location with given id
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value="/show")
    public static String getLocation(@RequestParam("id") String idString) throws IOException
    {
        try
        {
            int id = Integer.parseInt(idString);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            if (findById(getListOfLocations(), id) != null)
            {
                return ow.writeValueAsString(findById(getListOfLocations(), id));
            }
            else
                return "Brak lokacji o podanym id";
        }
        catch (java.lang.NumberFormatException e)
        {
            return "Id musi być liczbą";
        }
    }

    // ########## CALCULATIONS ##########

    // Returns AREA of the location with given id
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

    // Returns VOLUME of the location with given id
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

    // Returns LIGHT POWER of the location with given id
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

    // Returns HEATING USAGE of the location with given id
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
