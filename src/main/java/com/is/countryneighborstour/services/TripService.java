package com.is.countryneighborstour.services;

public interface TripService {
    String calculatePriceForCountry(String country, int totalBudget, int budgetPerCountry, String currency);
}