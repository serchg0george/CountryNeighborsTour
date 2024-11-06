package com.is.countryneighborstour.services;

import com.is.countryneighborstour.dto.CountryBordersDto;
import reactor.core.publisher.Flux;

public interface CountriesService {
    Flux<CountryBordersDto> getAllBorders(String countryCode);
}
