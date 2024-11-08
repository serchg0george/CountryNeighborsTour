package com.is.countryneighborstour.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    private Integer timesToVisit;

    @NotNull
    private Integer remainingBudget;

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    private List<CountryBudgetDto> countryBudgets;
}
