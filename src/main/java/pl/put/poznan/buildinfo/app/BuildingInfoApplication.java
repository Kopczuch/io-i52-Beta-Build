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

/**
 * BuildingInfo project main class, responsible for running the program
 *
 * @author Adam Kopiec, Jakub Kozłowski, Mikołaj Krakowiak
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildinfo.rest"})
public class BuildingInfoApplication {

    /**
     * Method responsible for running the programme.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BuildingInfoApplication.class, args);
    }
}
