package com.is.countryneighborstour.services;

public interface TripService {
    String calculatePriceForCountry(String country, Integer totalBudget, Integer budgetPerCountry, String currency);
}