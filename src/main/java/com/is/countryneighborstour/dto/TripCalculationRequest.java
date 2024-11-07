package com.is.countryneighborstour.dto;

import lombok.Getter;

/**
 *
 * This class contains details such as the destination country, the total budget, the
 * budget per country, and the currency in which the budget is represented.
 *
 **/

@Getter
public class TripCalculationRequest {
    private String country;
    private int totalBudget;
    private int budgetPerCountry;
    private String currency;
}
