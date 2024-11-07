package com.is.countryneighborstour.services;

import java.util.Map;

public interface ExchangeRateService {
    void saveExchangeRates(String baseCurrency, Map<String, Double> rates);
}
