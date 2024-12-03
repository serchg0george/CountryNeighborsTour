package com.is.countryneighborstour.controllers;

import com.is.countryneighborstour.dto.TripCalculationRequest;
import com.is.countryneighborstour.dto.TripCalculationResponse;
import com.is.countryneighborstour.services.TripService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling trip-related operations such as calculating the
 * price required to visit a country based on provided budget and currency.
 */

@RestController
@RequestMapping("/api/v1/trip")
@AllArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/calculate-trip")
    public ResponseEntity<TripCalculationResponse> calculatePriceForCountry(@RequestBody @Valid TripCalculationRequest request) {

        TripCalculationResponse response = tripService.calculatePriceForCountry(
                request.country(),
                request.totalBudget(),
                request.budgetPerCountry(),
                request.currency()
        );

        return ResponseEntity.ok(response);
    }
}
