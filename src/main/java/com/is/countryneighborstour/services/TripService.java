package com.is.countryneighborstour.services;

import com.is.countryneighborstour.dto.CountryBudgetDto;
import com.is.countryneighborstour.dto.TripCalculationResponse;

import java.math.BigDecimal;
import java.util.List;

public interface TripService {
    TripCalculationResponse calculatePriceForCountry(String country, Integer totalBudget, Integer budgetPerCountry, String currency);

    List<String> getBorderCountries(String country);

    List<CountryBudgetDto> calculateCountryBudgets(List<String> borders, Integer budgetPerCountry, String baseCurrency);

    BigDecimal calculateNeededLocalCurrency(BigDecimal rateValue, Integer budgetPerCountry);

    Integer[] calculateTripNumbers(Integer totalBudget, Integer budgetPerCountry, Integer neighborCount);
}