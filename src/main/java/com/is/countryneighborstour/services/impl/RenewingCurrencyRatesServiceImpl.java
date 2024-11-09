package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.services.CurrencyService;
import com.is.countryneighborstour.services.RenewingCurrencyRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RenewingCurrencyRatesServiceImpl implements RenewingCurrencyRatesService {

    private final CurrencyService currencyService;

    @Override
    public void updateCurrencyRates() {
        currencyService.getAllRates("EUR").subscribe();
    }
}
