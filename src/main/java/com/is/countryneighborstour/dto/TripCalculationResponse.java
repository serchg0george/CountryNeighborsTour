package com.is.countryneighborstour.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents the response for a trip calculation, including information on
 * the number of times a trip can be taken within a specified budget,
 * the remaining budget after planned trips, the currency used, and budget details for each country.
 */

@Getter
@Setter
@AllArgsConstructor
public class TripCalculationResponse {
    private Integer timesToVisit;
    private Integer remainingBudget;
    private String currency;
    private List<CountryBudgetDto> countryBudgets;
}
