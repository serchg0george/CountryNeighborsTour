package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.services.CountriesService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CountriesServiceImpl implements CountriesService {

    private final WebClient webClient = WebClient.create("https://restcountries.com/v3.1/name/");

    @Override
    public Flux<CountryInfoDto> getAllBordersTest(String countryCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(countryCode).build())
                .retrieve()
                .bodyToFlux(CountryInfoDto.class);
    }
}
