package com.is.countryneighborstour.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TripCalculationResponse {
    private Integer timesToVisit;
    private Integer remainingBudget;
    private String currency;
    private List<CountryBudget> countryBudgets;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CountryBudget {
        private String country;
        private String currency;
        private BigDecimal requiredBudget;
    }
}
