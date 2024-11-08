package com.is.countryneighborstour.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents the structure of an exception response, including HTTP status,
 * error message, and timestamp formatted as dd-MM-yyyy hh:mm:ss.
 */
public record ExceptionResponse(HttpStatus status, String message,
                                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
                                LocalDateTime dateTime) {
}
