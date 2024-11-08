package com.is.countryneighborstour.exceptions;

/**
 * Exception thrown when an invalid or unrecognized currency code is provided.
 */
public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String currency) {
        super("Currency with code: " + currency + " is not a valid");
    }
}
