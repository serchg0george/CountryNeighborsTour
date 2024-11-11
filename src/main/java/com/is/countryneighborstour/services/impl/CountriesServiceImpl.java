package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.exceptions.TripCountryBadRequestException;
import com.is.countryneighborstour.exceptions.TripCountryNotFoundException;
import com.is.countryneighborstour.services.CountriesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * This service uses a WebClient to fetch country data from the REST Countries API
 * (https://restcountries.com/v3.1/name/). The service can retrieve information for a specified country
 * and returns it as a stream of {@link CountryInfoDto} objects.
 */

@Service
public class CountriesServiceImpl implements CountriesService {

    @Value("${rest-countries.api.address}")
    private String restCountriesApiAddress;

    private final WebClient webClient;

    public CountriesServiceImpl(@Value("${rest-countries.api.address}") String restCountriesApiAddress) {
        this.restCountriesApiAddress = restCountriesApiAddress;
        this.webClient = WebClient.create(restCountriesApiAddress);
    }

    @Override
    public CountryInfoDto getAllBorders(String countryCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(countryCode).build())
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode == HttpStatusCode.valueOf(404), response -> response.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new TripCountryNotFoundException(countryCode))))
                .onStatus(httpStatusCode -> httpStatusCode == HttpStatusCode.valueOf(400), response -> response.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new TripCountryBadRequestException(countryCode))))
                .bodyToFlux(CountryInfoDto.class).blockFirst();
    }
}
