package com.is.countryneighborstour.dto;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CountryInfoDto {
    List<String> borders;
    Map<String, CurrencyDto> currencies;
}
