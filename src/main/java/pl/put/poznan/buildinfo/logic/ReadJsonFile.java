package pl.put.poznan.buildinfo.logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.*;

import java.util.List;
import java.util.Map;

public class ReadJsonFile {
    
    
    static ObjectMapper objectMapper = new ObjectMapper();
    static TypeReference<List<Building>> typeReference = new TypeReference<List<Building>>() {};
    
    public static List<Building> getBuildings() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/locations2.json"), typeReference);
    }

    // public static List<Floor> getFloors() throws IOException {

    // }

}
