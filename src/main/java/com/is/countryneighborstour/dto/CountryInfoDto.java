package com.is.countryneighborstour.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * This class provides details such as the list of bordering countries and a map of the country's currencies.
 * The currency data is represented by {@link CurrencyDto} objects.
 */


@Getter
public class CountryInfoDto {

    @NotNull
    private List<String> borders;

    @NotNull
    private Map<String, CurrencyDto> currencies;
}
