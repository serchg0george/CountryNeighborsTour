package com.is.countryneighborstour.exceptions;

/**
 * Exception thrown when a requested country is not found during trip calculations.
 */
public class TripCountryNotFoundException extends RuntimeException {
    public TripCountryNotFoundException(String country) {
        super("Country with code: " + country + " not found");
    }
}
