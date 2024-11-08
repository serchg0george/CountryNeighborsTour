package com.is.countryneighborstour.dto;

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
    private String country;
    private String currency;
    private BigDecimal requiredBudget;
}
