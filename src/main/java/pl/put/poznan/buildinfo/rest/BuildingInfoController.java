package pl.put.poznan.buildinfo.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    static Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
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
        logger.info("findById, id: " + id);
        for (Location o : list) {
            if (o.getId() == id) {
                logger.debug("findById, found");
                return o;
            }
            if (o.getNestedList() != null) {
                List<Location> _o = o.getNestedList();
                Location result = findById(_o, id);
                if (result != null) {
                    logger.debug("findById, found");
                    return result;
                }
            }
        }
        logger.debug("findById, cannot find location with id: " + id);
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
        logger.info("root");
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
     * @throws IOException when it cannot get list of locations
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/ListOfLocations")
    public static List<Location> getListOfLocations() throws IOException {
        logger.debug("getListOfLocations");
        return objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceLocation);
    }

    /**
     * Method implementing 'show' endpoint.
     * Displays the data of a location with the specified id.
     *
     * @param idString location id
     * @return locationInfo information on the location with the given id
     * @throws IOException when it cannot find a particular location, or an id is given in an incorrect format
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/show")
    public static String getLocation(@RequestParam("id") String idString) throws IOException {
        logger.info("getLocation, id: " + idString);
        try {
            int id = Integer.parseInt(idString);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            if (findById(getListOfLocations(), id) != null) {
                logger.debug("getLocation, got location");
                return ow.writeValueAsString(findById(getListOfLocations(), id));
            } else {
                logger.debug("getLocation, location with id: " + idString + " cannot be found");
                return "Brak lokacji o podanym id";
            }
        } catch (java.lang.NumberFormatException e) {
            logger.debug("getLocation, id must be a number!");
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
     * @throws IOException when it cannot find a particular location, or an id is given in an incorrect format
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_area")
    public static String getArea(@RequestParam("id") String idString) throws IOException {
        logger.info("getArea, id: " + idString);
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateArea();
            logger.debug("getArea, got location area: " + x);
            return "Powierzchnia lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            logger.debug("getArea, location with id: " + idString + " cannot be found");
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            logger.debug("getArea, id must be a number!");
            return "Id musi być liczbą";
        }

    }

    /**
     * Method implementing 'get_volume' endpoint.
     * Displays the volume of the location with the specified id.
     *
     * @param idString location id
     * @return locationVolume volume of the location
     * @throws IOException when it cannot find a particular location, or an id is given in an incorrect format
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_volume")
    public static String getVolume(@RequestParam("id") String idString) throws IOException {
        logger.info("getVolume, id: " + idString);
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateVolume();
            logger.debug("getVolume, got location volume: " + x);
            return "Objetosc lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            logger.debug("getVolume, location with id: " + idString + " cannot be found");
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            logger.debug("getVolume, id must be a number!");
            return "Id musi być liczbą";
        }
    }

    /**
     * Method implementing 'get_light_power' endpoint.
     * Displays the light power of the location with the specified id.
     *
     * @param idString location id
     * @return locationLightPower light power of the location
     * @throws IOException when it cannot find a particular location, or an id is given in an incorrect format
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_light_power")
    public static String getLightPower(@RequestParam("id") String idString) throws IOException {
        logger.info("getLightPower, id: " + idString);
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateLightPower();
            logger.debug("getLightPower, got light power: " + x);
            return "Moc oswietlenia w przeliczeniu na jednostke powierzchni lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            logger.debug("getLightPower, location with id: " + idString + " cannot be found");
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            logger.debug("getLightPower, id must be a number!");
            return "Id musi być liczbą";
        }
    }

    /**
     * Method implementing 'get_heating_usage' endpoint.
     * Displays the energy consumption for heating of the location with the given id.
     *
     * @param idString location id
     * @return energyConsumptionHeating energy consumption for heating of the location
     * @throws IOException when it cannot find a particular location, or an id is given in an incorrect format
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/get_heating_usage")
    public static String getHeatingUsage(@RequestParam("id") String idString) throws IOException {
        logger.info("getHeatingUsage, id: " + idString);
        try {
            int id = Integer.parseInt(idString);
            double x = findById(getListOfLocations(), id).calculateHeatingUsage();
            logger.debug("getHeatingUsage, got heating usage: " + x);
            return "Zuzycie energii na ogrzewanie w przeliczeniu na jednostke objetosci lokacji o id = " + id + " to:\n" + x;
        } catch (java.lang.NullPointerException e) {
            logger.debug("getHeatingUsage, location with id: " + idString + " cannot be found");
            return "Brak lokacji o podanym id";
        } catch (java.lang.NumberFormatException e) {
            logger.debug("getHeatingUsage, id must be a number!");
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
     * @throws IOException when it cannot find a particular location, or an id is given in an incorrect format
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/heating_over_limit")
    public static List<Room> HeatingOverdose(@RequestParam("limit") double limit, @RequestParam("id") int id) throws IOException {
        logger.info("HeatingOverdose, limit: " + limit + ", id: " + id);
        Building building = objectMapper.readValue(new File("src/main/resources/locations.json"), typeReferenceBuilding).stream().filter(_building -> _building.getId() == id).findFirst().orElse(null);
        List<Room> Overdose = new ArrayList<>();
        for (Floor f : building.getFloors()) {
            for (Room r : f.getRooms()) {
                if (r.calculateHeatingUsage() > limit) {
                    Overdose.add(r);
                }
            }
        }
        logger.debug("HeatingOverdose, found " + Overdose.size() + " location/locations");
        return Overdose;
    }
}
