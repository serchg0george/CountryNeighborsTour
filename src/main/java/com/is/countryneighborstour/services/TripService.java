package com.is.countryneighborstour.services;

import com.is.countryneighborstour.dto.TripCalculationResponse;

public interface TripService {
    TripCalculationResponse calculatePriceForCountry(String country, Integer totalBudget, Integer budgetPerCountry, String currency);

    String findLocalCurrency(String country);
}