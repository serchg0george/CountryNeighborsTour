package com.is.countryneighborstour.controllers;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.services.CountriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling country-related operations, such as retrieving country
 * information and neighboring countries based on a provided country code.
 */

@RestController
@RequestMapping("/api/v1/countries")
@AllArgsConstructor
public class CountriesController {

    private final CountriesService countriesService;

    @GetMapping("/get-country/{countryCode}")
    public ResponseEntity<CountryInfoDto> getAllCountries(@PathVariable String countryCode) {
        return ResponseEntity.ok(countriesService.getAllBorders(countryCode));
    }
}
