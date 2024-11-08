package com.is.countryneighborstour.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * This class provides information about the currency's name and symbol.
 */

@Getter
public class CurrencyDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    private String symbol;
}
