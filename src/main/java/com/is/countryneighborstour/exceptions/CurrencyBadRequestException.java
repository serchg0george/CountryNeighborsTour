package com.is.countryneighborstour.exceptions;

/**
 * Exception thrown when an invalid or unrecognized currency code is provided.
 */
public class CurrencyBadRequestException extends RuntimeException {
    public CurrencyBadRequestException(String currency) {
        super("Currency with code: " + currency + " is not a valid");
    }
}
