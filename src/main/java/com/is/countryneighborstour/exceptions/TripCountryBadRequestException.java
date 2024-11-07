package com.is.countryneighborstour.exceptions;

public class TripCountryBadRequestException extends RuntimeException {
    public TripCountryBadRequestException(String country) {
        super("Country with code: " + country + " not found");
    }
}
