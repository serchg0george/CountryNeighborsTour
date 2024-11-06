package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.services.CountriesService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Service implementation for retrieving country information, including border details, based on country codes.
 *
 * This service uses a WebClient to fetch country data from the REST Countries API
 * (https://restcountries.com/v3.1/name/). The service can retrieve information for a specified country
 * and returns it as a stream of {@link CountryInfoDto} objects.
 *
 **/

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
