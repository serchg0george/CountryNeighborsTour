package com.is.countryneighborstour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CountryNeighborsTourApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryNeighborsTourApplication.class, args);
    }

}
