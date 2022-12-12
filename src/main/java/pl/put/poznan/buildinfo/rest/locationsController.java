package pl.put.poznan.buildinfo.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

import pl.put.poznan.buildinfo.logic.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.security.auth.x500.X500Principal;

@RestController
public class locationsController {
    
    static ObjectMapper objectMapper = new ObjectMapper();

    static TypeReference<List<Building>> typeReferenceBuilding = new TypeReference<List<Building>>() {};
    static TypeReference<List<Floor>> typeReferenceFloor = new TypeReference<List<Floor>>() {};
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/ListOfBuildings")
    public static List<Building> getListOfBuildings() throws IOException {
        List<Building> listaBudynkow = objectMapper.readValue(new File("src/main/resources/locations2.json"), typeReferenceBuilding);
        return listaBudynkow;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/building-{id}")
    public static Building getBuilding(@PathVariable("id") int x) throws IOException {
        Building budynek = getListOfBuildings().stream().filter(_building -> _building.getId() == x).findFirst().orElse(null);
        return budynek;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/building-{id}-floors")
    public static List<Floor> getListOfFloors(@PathVariable("id") int x) throws IOException {
        List<Floor> listaPieter = getBuilding(x).getFloors();
        return listaPieter;
    }

}
