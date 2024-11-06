package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.RatesDto;
import com.is.countryneighborstour.services.CurrencyService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Service implementation for handling currency exchange rate data.
 *
 * This service uses a WebClient to fetch the latest exchange rates from an external API
 * (http://data.fixer.io/api/latest). The service can retrieve all exchange rates based on
 * a specified base currency and convert them to a Flux of {@link RatesDto}.
 *
 **/

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final WebClient webClient = WebClient.create("http://data.fixer.io/api/latest");

    @Override
    public Flux<RatesDto> getAllRates(String baseCurrency) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("&base=" + baseCurrency)
                        .queryParam("access_key", "4bca71fb7d35a64e8178b152bb558a55")
                        .build())
                .retrieve()
                .bodyToFlux(RatesDto.class);
    }

}
