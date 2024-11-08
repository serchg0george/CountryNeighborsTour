package com.is.countryneighborstour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Represents the budget details for a specific country, including
 * the country name, currency, and the budget required for visiting.
 */

@Getter
@Setter
@AllArgsConstructor
public class CountryBudgetDto {

    @NotBlank
    @Size(min = 2, max = 3)
    private String country;

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    private BigDecimal requiredBudget;
}
