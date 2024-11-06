package com.is.countryneighborstour.services;

import com.is.countryneighborstour.dto.CountryInfoDto;
import reactor.core.publisher.Flux;

public interface CountriesService {
    Flux<CountryInfoDto> getAllBordersTest(String countryCode);
}
