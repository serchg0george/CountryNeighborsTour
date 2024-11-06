package com.is.countryneighborstour.services;

import com.is.countryneighborstour.dto.CountryInfoDto;

public interface CountriesService {
    CountryInfoDto getAllBorders(String countryCode);
}
