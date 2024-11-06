package com.is.countryneighborstour.controllers;

import com.is.countryneighborstour.dto.CountryBordersDto;
import com.is.countryneighborstour.services.impl.CountriesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/countries")
@AllArgsConstructor
public class CountriesController {

    private final CountriesServiceImpl countriesService;

    @GetMapping("/get_country/{countryCode}")
    public Flux<CountryBordersDto> getAllCountries(@PathVariable String countryCode) {
        return countriesService.getAllBorders(countryCode);
    }
}
