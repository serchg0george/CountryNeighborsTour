package com.is.countryneighborstour.controllers;

import com.is.countryneighborstour.dto.RatesDto;
import com.is.countryneighborstour.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/currencies")
@AllArgsConstructor
public class CurrenciesController {

    private final CurrencyService currencyService;

    @GetMapping("/get_rates/{baseCurrency}")
    public Flux<RatesDto> getRates(@PathVariable String baseCurrency) {
        return currencyService.getAllRates(baseCurrency);
    }
}
