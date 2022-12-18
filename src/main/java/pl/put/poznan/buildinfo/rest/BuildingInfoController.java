package pl.put.poznan.buildinfo.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import pl.put.poznan.buildinfo.logic.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the BuildingInfoController class responsible for controlling the REST API interface
 *
 * @author Jakub Kozłowski, Adam Kopiec, Mikołaj Krakowiak
 * @version 2.0
 */
@RestController
public class BuildingInfoController {

    static ObjectMapper objectMapper = new ObjectMapper();
    static TypeReference<List<Location>> typeReferenceLocation = new TypeReference<List<Location>>() {
    };
    static TypeReference<List<Building>> typeReferenceBuilding = new TypeReference<List<Building>>() {
    };

    /**
     * Method to find locations with a specific id.
     *
     * @param list list of available locations
     * @param id   id of a given location
     * @return location or null when there is no location with a specific id
     */
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

    // ########## LOCATIONS ##########

    /**
     * Method implementing the root endpoint, presenting information on available endpoints.
     *
     * @return rootMessage message containing information on available endpoints
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/")
    public String root() {
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

    /**
     * Method implementing 'ListOfLocations' endpoint, providing information on all locations in JSON format.
     *
     * @return listOfLocations list of locations in JSON format
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/ListOfLocations")
    public static List<Location> getListOfLocations() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceLocation);
    }

    /**
     * Method implementing 'show' endpoint.
     * Displays the data of a location with the specified id.
     *
     * @param idString location id
     * @return locationInfo information on the location with the given id
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/show")
    public static String getLocation(@RequestParam("id") String idString) throws IOException {
        try {
            int id = Integer.parseInt(idString);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            if (findById(getListOfLocations(), id) != null) {
                return ow.writeValueAsString(findById(getListOfLocations(), id));
            } else
                return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            return "Id musi być liczbą";
        }
    }

    // ########## CALCULATIONS ##########

    /**
     * Method implementing 'get_area' endpoint.
     * Displays the area of the location with the specified id.
     *
     * @param idString location id
     * @return locationArea area of the location
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_area")
    public static String getArea(@RequestParam("id") String idString) throws IOException {
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateArea();
            return "Powierzchnia lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            return "Id musi być liczbą";
        }

    }

    /**
     * Method implementing 'get_volume' endpoint.
     * Displays the volume of the location with the specified id.
     *
     * @param idString location id
     * @return locationVolume volume of the location
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_volume")
    public static String getVolume(@RequestParam("id") String idString) throws IOException {
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateVolume();
            return "Objetosc lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            return "Id musi być liczbą";
        }
    }

    /**
     * Method implementing 'get_light_power' endpoint.
     * Displays the light power of the location with the specified id.
     *
     * @param idString location id
     * @return locationLightPower light power of the location
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_light_power")
    public static String getLightPower(@RequestParam("id") String idString) throws IOException {
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateLightPower();
            return "Moc oswietlenia w przeliczeniu na jednostke powierzchni lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            return "Id musi być liczbą";
        }
    }

    /**
     * Method implementing 'get_heating_usage' endpoint.
     * Displays the energy consumption for heating of the location with the given id.
     *
     * @param idString location id
     * @return energyConsumptionHeating energy consumption for heating of the location
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_heating_usage")
    public static String getHeatingUsage(@RequestParam("id") String idString) throws IOException {
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateHeatingUsage();
            return "Zuzycie energii na ogrzewanie w przeliczeniu na jednostke objetosci lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            return "Id musi być liczbą";
        }
    }

    /**
     * Method implementing 'heating_over_limit' endpoint.
     * Display rooms which exceed the specified energy consumption limit for heating in a building with the given id.
     *
     * @param limit energy consumption limit
     * @param id    building id
     * @return roomsOverdose rooms with heating usage over given limit
     * @throws IOException if there is no building with given id
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/heating_over_limit")
    public static List<Room> HeatingOverdose(@RequestParam("limit") double limit, @RequestParam("id") int id) throws IOException {
        Building building = objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceBuilding).stream().filter(_building -> _building.getId() == id).findFirst().orElse(null);
        List<Room> Overdose = new ArrayList<>();
        for (Floor f : building.getFloors()) {
            for (Room r : f.getRooms()) {
                if (r.calculateHeatingUsage() > limit) {
                    Overdose.add(r);
                }
            }
        }
        return Overdose;
    }
}
