package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.exceptions.TripCountryBadRequestException;
import com.is.countryneighborstour.services.CountriesService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CountriesServiceImpl implements CountriesService {

    @Value("${rest-countries.api.address}")
    private String restCountriesApiAddress;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.create(restCountriesApiAddress);
    }

    @Override
    public CountryInfoDto getAllBorders(String countryCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(countryCode).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new TripCountryBadRequestException(countryCode))))
                .bodyToFlux(CountryInfoDto.class).blockFirst();
    }
}
