package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryBudgetDto;
import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.dto.TripCalculationResponse;
import com.is.countryneighborstour.entities.CountryCurrency;
import com.is.countryneighborstour.entities.ExchangeRates;
import com.is.countryneighborstour.repositories.CountryCurrencyRepository;
import com.is.countryneighborstour.repositories.ExchangeRateRepository;
import com.is.countryneighborstour.services.CountriesService;
import com.is.countryneighborstour.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service implementation for handling trip calculations, including budget allocation per country,
 * determining the number of trips possible within a given budget, and currency conversions.
 */

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    private final CountriesService countriesService;
    private final ExchangeRateRepository exchangeRateRepository;
    private final CountryCurrencyRepository countryCurrencyRepository;

    @Override
    public TripCalculationResponse calculatePriceForCountry(String country, Integer totalBudget, Integer budgetPerCountry, String baseCurrency) {
        List<String> borders = getBorderCountries(country);

        List<CountryBudgetDto> countryBudgets = calculateCountryBudgets(borders, budgetPerCountry, baseCurrency);

        Integer[] frequencyData = calculateTripNumbers(totalBudget, budgetPerCountry, borders.size());
        Integer timesToVisit = frequencyData[0];
        Integer remainingBudget = frequencyData[1];

        return new TripCalculationResponse(timesToVisit, remainingBudget, baseCurrency, countryBudgets);
    }

    @Override
    public List<String> getBorderCountries(String country) {
        CountryInfoDto countryInfo = countriesService.getAllBorders(country);
        return countryInfo.borders();
    }

    @Override
    public List<CountryBudgetDto> calculateCountryBudgets(List<String> borders, Integer budgetPerCountry, String baseCurrency) {

        Map<String, CountryBudgetDto> countryBudgets = borders.stream()
                .collect(Collectors.toMap(border -> border, border -> new CountryBudgetDto()));

        Map<String, String> countryCurrencyMap = countryCurrencyRepository.findByCountryCodeIn(borders).stream()
                .collect(Collectors.toMap(CountryCurrency::getCountryCode, CountryCurrency::getCurrencyCode));

        List<String> targetCurrencies = new ArrayList<>(countryCurrencyMap.values());
        List<ExchangeRates> exchangeRates = exchangeRateRepository.findByBaseCurrencyAndTargetCurrencyInAndDate(baseCurrency, targetCurrencies, LocalDate.now());

        Map<String, BigDecimal> exchangeRateMap = exchangeRates.stream()
                .collect(Collectors.toMap(ExchangeRates::getTargetCurrency, ExchangeRates::getRate));

        countryBudgets.forEach((country, countryData) -> {
            countryData.setCurrency(countryCurrencyMap.get(country));
            countryData.setCountry(country);
            countryData.setRequiredBudget(calculateNeededLocalCurrency(exchangeRateMap.get(countryData.getCurrency()), budgetPerCountry));
        });


        return new ArrayList<>(countryBudgets.values());
    }



    @Override
    public BigDecimal calculateNeededLocalCurrency(BigDecimal rateValue, Integer budgetPerCountry) {
        return rateValue.multiply(BigDecimal.valueOf(budgetPerCountry)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Integer[] calculateTripNumbers(Integer totalBudget, Integer budgetPerCountry, Integer neighborCount) {
        Integer totalTripCost = neighborCount * budgetPerCountry;
        Integer timesToVisit = totalBudget / totalTripCost;
        Integer remainingBudget = totalBudget % totalTripCost;
        return new Integer[]{timesToVisit, remainingBudget};
    }

}
