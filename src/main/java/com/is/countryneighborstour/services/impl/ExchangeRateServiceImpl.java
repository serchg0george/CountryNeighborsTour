package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.entities.ExchangeRates;
import com.is.countryneighborstour.repositories.ExchangeRateRepository;
import com.is.countryneighborstour.services.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

/**
 * This service interacts with the {@link ExchangeRateRepository} to save exchange
 * rate data, including base and target currencies along with their rates.
 */

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void saveExchangeRates(String baseCurrency, Map<String, BigDecimal> rates) {
        rates.forEach((targetCurrency, rate) -> {
            ExchangeRates exchangeRates = ExchangeRates.builder()
                    .baseCurrency(baseCurrency)
                    .targetCurrency(targetCurrency)
                    .rate(rate)
                    .date(Date.valueOf(LocalDate.now()).toLocalDate())
                    .build();
            exchangeRateRepository.save(exchangeRates);
        });
    }
}
