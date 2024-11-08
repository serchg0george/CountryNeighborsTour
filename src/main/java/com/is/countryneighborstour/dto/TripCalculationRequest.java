package com.is.countryneighborstour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * This class contains details such as the destination country, the total budget, the
 * budget per country, and the currency in which the budget is represented.
 */

@Getter
public class TripCalculationRequest {

    @NotBlank
    @Size(min = 2, max = 3)
    private String country;

    @NotNull
    private Integer totalBudget;

    @NotNull
    private Integer budgetPerCountry;

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;
}
