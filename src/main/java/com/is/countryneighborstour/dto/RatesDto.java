package com.is.countryneighborstour.dto;

import lombok.Getter;

import java.util.Map;

/**
 *
 * This class provides a map of currency codes to exchange rates, where each rate is expressed
 * as a {@link Double} value relative to a specified base currency.
 *
 */

@Getter
public class RatesDto {
    Map<String, Double> rates;
}
