package com.is.countryneighborstour.services;

import java.math.BigDecimal;
import java.util.Map;

public interface ExchangeRateService {
    void saveExchangeRates(String baseCurrency, Map<String, BigDecimal> rates);
}
