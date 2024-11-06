package com.is.countryneighborstour.controllers;

import com.is.countryneighborstour.dto.TripCalculationRequest;
import com.is.countryneighborstour.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trip")
@AllArgsConstructor()
public class TripController {

    TripService tripService;

    @GetMapping("/calculate-trip")
    public ResponseEntity<String> calculatePriceForCountry(@RequestBody TripCalculationRequest request) {
        return ResponseEntity.ok(tripService.calculatePriceForCountry(
                request.getCountry(),
                request.getTotalBudget(),
                request.getBudgetPerCountry(),
                request.getCurrency()
        ));
    }
}
