package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.services.CountriesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Service implementation for retrieving country information, including border details, based on country codes.
 *
 * This service uses a WebClient to fetch country data from the REST Countries API
 * (https://restcountries.com/v3.1/name/). The service can retrieve information for a specified country
 * and returns it as a stream of {@link CountryInfoDto} objects.
 *
 **/

@Service
@PropertySource("classpath:application.properties")
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
                .bodyToFlux(CountryInfoDto.class).blockFirst();
    }
}
