package com.is.countryneighborstour.exceptions;

public class CurrencyBadRequestException extends RuntimeException {
    public CurrencyBadRequestException(String currency) {
        super("Currency with code: " + currency + " is not a valid");
    }
}
