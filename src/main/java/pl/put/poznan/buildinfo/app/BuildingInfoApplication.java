package pl.put.poznan.buildinfo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildinfo.rest"})
@RestController
public class BuildingInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingInfoApplication.class, args);
    }

    @GetMapping
    public String hello() {
        return "Hello World!";
    }
}
