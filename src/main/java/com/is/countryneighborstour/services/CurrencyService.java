package com.is.countryneighborstour.services;

import com.is.countryneighborstour.dto.RatesDto;
import reactor.core.publisher.Flux;

public interface CurrencyService {
    Flux<RatesDto> getAllRates(String baseCurrency);
}
