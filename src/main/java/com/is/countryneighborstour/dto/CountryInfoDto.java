package com.is.countryneighborstour.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * This class provides details about a list of bordering countries
 */


public record CountryInfoDto(@NotNull List<String> borders) {
}
