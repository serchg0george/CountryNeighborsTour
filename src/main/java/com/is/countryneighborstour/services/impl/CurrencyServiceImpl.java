package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.RatesDto;
import com.is.countryneighborstour.exceptions.CurrencyBadRequestException;
import com.is.countryneighborstour.services.CurrencyService;
import com.is.countryneighborstour.services.ExchangeRateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service implementation for handling currency exchange rate data.
 * <p>
 * This service uses a WebClient to fetch the latest exchange rates from an external API
 * (http://data.fixer.io/api/latest). The service can retrieve all exchange rates based on
 * a specified base currency and convert them to a Flux of {@link RatesDto}.
 **/

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${fixer.api.address}")
    private String fixerApiAddress;

    @Value("${fixer.api.key}")
    private String fixerApiKey;

    private WebClient webClient;

    ExchangeRateService exchangeRateService;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.create(fixerApiAddress);
    }

    @Override
    public Flux<RatesDto> getAllRates(String baseCurrency) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("&base=" + baseCurrency)
                        .queryParam("access_key", fixerApiKey)
                        .build())
                .retrieve()
                .bodyToFlux(RatesDto.class)
                .flatMap(ratesDto -> {
                    if (ratesDto.getRates() == null || ratesDto.getRates().isEmpty()) {
                        return Mono.error(new CurrencyBadRequestException(baseCurrency));
                    }
                    return Mono.just(ratesDto);
                })
                .doOnNext(ratesDto -> exchangeRateService.saveExchangeRates(baseCurrency, ratesDto.getRates()));
    }

}
