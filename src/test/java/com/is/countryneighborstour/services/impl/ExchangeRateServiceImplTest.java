package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.entities.ExchangeRates;
import com.is.countryneighborstour.repositories.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;


    @Test
    void testSaveExchangeRatesSuccess() {

        String baseCurrency = "USD";
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("EUR", new BigDecimal("0.85"));
        rates.put("GBP", new BigDecimal("0.75"));

        exchangeRateService.saveExchangeRates(baseCurrency, rates);

        verify(exchangeRateRepository, times(1)).save(argThat(exchangeRates ->
                exchangeRates.getBaseCurrency().equals("USD") &&
                        exchangeRates.getTargetCurrency().equals("EUR") &&
                        exchangeRates.getRate().equals(new BigDecimal("0.85")) &&
                        exchangeRates.getDate().equals(LocalDate.now())
        ));

        verify(exchangeRateRepository, times(1)).save(argThat(exchangeRates ->
                exchangeRates.getBaseCurrency().equals("USD") &&
                        exchangeRates.getTargetCurrency().equals("GBP") &&
                        exchangeRates.getRate().equals(new BigDecimal("0.75")) &&
                        exchangeRates.getDate().equals(LocalDate.now())
        ));
    }

    @Test
    void testSaveExchangeRatesFailed() {

        String baseCurrency = "USD";
        Map<String, BigDecimal> rates = new HashMap<>();

        exchangeRateService.saveExchangeRates(baseCurrency, rates);

        verify(exchangeRateRepository, never()).save(any(ExchangeRates.class));
    }

}