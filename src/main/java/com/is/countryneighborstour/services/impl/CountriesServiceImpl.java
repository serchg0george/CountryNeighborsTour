package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryBordersDto;
import com.is.countryneighborstour.services.CountriesService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CountriesServiceImpl implements CountriesService {

    private final WebClient webClient = WebClient.create("https://api.geodatasource.com/neighboring-countries");

    public Flux<CountryBordersDto> getAllBorders(String countryCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("&format=json&country_code=" + countryCode)
                        .queryParam("key", "0RTFDQ0N0EVGGENBRUPDEWFKPTY631VY")
                        .build())
                .retrieve()
                .bodyToFlux(CountryBordersDto.class);
    }

}
