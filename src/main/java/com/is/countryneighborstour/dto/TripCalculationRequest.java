package com.is.countryneighborstour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * This class contains details such as the destination country, the total budget, the
 * budget per country, and the currency in which the budget is represented.
 */

public record TripCalculationRequest(@NotBlank @Size(min = 2, max = 3) String country,

                                     @NotNull Integer totalBudget,

                                     @NotNull Integer budgetPerCountry,

                                     @NotBlank @Size(min = 3, max = 3) String currency) {
}
