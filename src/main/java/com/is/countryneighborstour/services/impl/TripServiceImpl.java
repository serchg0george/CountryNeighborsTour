package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.dto.TripCalculationResponse;
import com.is.countryneighborstour.entities.ExchangeRates;
import com.is.countryneighborstour.repositories.ExchangeRateRepository;
import com.is.countryneighborstour.services.CountriesService;
import com.is.countryneighborstour.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    private final CountriesService countriesService;
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public TripCalculationResponse calculatePriceForCountry(String country, Integer totalBudget, Integer budgetPerCountry, String baseCurrency) {

        CountryInfoDto countryInfo = countriesService.getAllBorders(country);
        List<String> borders = countryInfo.getBorders();

        Map<String, BigDecimal> currenciesMap = new HashMap<>();

        List<TripCalculationResponse.CountryBudget> countryBudgets = new ArrayList<>();

        for (String border : borders) {
            String localCurrency = findLocalCurrency(border);
            ExchangeRates rate = exchangeRateRepository.findByBaseCurrencyAndTargetCurrencyAndDate(baseCurrency, localCurrency, LocalDate.now());

            if (rate != null) {
                BigDecimal rateValue = rate.getRate();
                BigDecimal neededLocalCurrency = BigDecimal.valueOf(rateValue.longValue() * budgetPerCountry).setScale(2, RoundingMode.HALF_UP);
                currenciesMap.put(localCurrency, neededLocalCurrency);
                countryBudgets.add(new TripCalculationResponse.CountryBudget(border, localCurrency, neededLocalCurrency));
            }
        }

        Integer neighborCount = borders.size();
        Integer timesToVisit = totalBudget / (neighborCount * budgetPerCountry);
        Integer remainingBudget = totalBudget % (neighborCount * budgetPerCountry);

        return new TripCalculationResponse(timesToVisit, remainingBudget, baseCurrency, countryBudgets);
    }

    @Override
    public String findLocalCurrency(String country) {
        return countriesService.getAllBorders(country).getCurrencies().keySet().stream().findFirst().orElse("Unknown");
    }

}
