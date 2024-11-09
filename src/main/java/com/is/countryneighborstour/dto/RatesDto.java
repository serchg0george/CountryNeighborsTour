package com.is.countryneighborstour.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Map;

/**
 * This class provides a map of currency codes to exchange rates, where each rate is expressed
 * as a {@link Double} value relative to a specified base currency.
 */

public record RatesDto(@NotNull Map<String, BigDecimal> rates) {
}
