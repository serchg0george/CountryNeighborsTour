package com.is.countryneighborstour.dto;

import lombok.Getter;

@Getter
public class TripCalculationRequest {
    private String country;
    private int totalBudget;
    private int budgetPerCountry;
    private String currency;
}
