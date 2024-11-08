package com.is.countryneighborstour.exceptions;

public class TripCountryBadRequestException extends RuntimeException {
    public TripCountryBadRequestException(String message) {
        super("Invalid data: " + message + " country code size should be between 2 and 3");
    }
}
