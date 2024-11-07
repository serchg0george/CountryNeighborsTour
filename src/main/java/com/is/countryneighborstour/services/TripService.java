package com.is.countryneighborstour.services;

import java.util.List;
import java.util.Map;

public interface TripService {
    String calculatePriceForCountry(String country, Integer totalBudget, Integer budgetPerCountry, String currency);
    String findLocalCurrency(String country);
    String printCountryWithBudgetPerCountry(Map<String, Double> map, List<String> countries);
}