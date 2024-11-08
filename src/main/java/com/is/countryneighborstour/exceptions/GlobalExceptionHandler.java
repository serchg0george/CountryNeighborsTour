package com.is.countryneighborstour.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Global exception handler that handles custom exceptions and returns a structured
 * response with appropriate HTTP status and error details.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TripCountryBadRequestException.class)
    ResponseEntity<Object> handleCountryNotFoundException(TripCountryBadRequestException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(CurrencyBadRequestException.class)
    ResponseEntity<Object> handleCurrencyNotFoundException(CurrencyBadRequestException request) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, request.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }
}
