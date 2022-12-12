package pl.put.poznan.buildinfo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.put.poznan.buildinfo.logic.*;

import java.io.IOException;
import java.util.List;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildinfo.rest"})
public class BuildingInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingInfoApplication.class, args);
    }

    // @GetMapping("/budynki")
    // public List<Building> buildings() throws IOException {
    //     return ReadJsonFile.getListOfBuildings();
    // }

    // @GetMapping("/budynek-0")
    // public Building oneBuilding() throws IOException {
    //     return ReadJsonFile.getBuilding(0);
    // }

    // @GetMapping("/pietra-budynek-0")
    // public List<Floor> floors() throws IOException {
    //     return ReadJsonFile.getListOfFloors(0);
    // }
    // @GetMapping("/test")
    // public static String hello() {
    //     return "Testujemy czy dziala";
    // }
}
