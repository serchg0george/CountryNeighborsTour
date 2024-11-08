package com.is.countryneighborstour.controllers;

import com.is.countryneighborstour.dto.RatesDto;
import com.is.countryneighborstour.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Controller for handling currency-related operations, such as fetching exchange rates
 * based on a specified base currency.
 */

@RestController
@RequestMapping("/api/v1/currencies")
@AllArgsConstructor
public class CurrenciesController {

    private final CurrencyService currencyService;

    @GetMapping("/get-rates/{baseCurrency}")
    public ResponseEntity<Flux<RatesDto>> getRates(@PathVariable String baseCurrency) {
        return ResponseEntity.ok(currencyService.getAllRates(baseCurrency));
    }
}
