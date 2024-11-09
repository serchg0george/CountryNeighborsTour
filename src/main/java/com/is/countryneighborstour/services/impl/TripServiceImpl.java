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
import java.util.ArrayList;
import java.util.List;

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
        List<CountryBudgetDto> countryBudgets = new ArrayList<>();

        for (String border : borders) {
            CountryCurrency countryCurrency = countryCurrencyRepository.findByCountryCode(border);
            String localCurrency = countryCurrency.getCurrencyCode();

            if (localCurrency.equals(baseCurrency)) {
                countryBudgets.add(new CountryBudgetDto(border, localCurrency, BigDecimal.valueOf(budgetPerCountry)));
            } else {

                ExchangeRates rate = exchangeRateRepository.findByBaseCurrencyAndTargetCurrencyAndDate(baseCurrency, localCurrency, LocalDate.now());

                if (rate != null) {
                    BigDecimal neededLocalCurrency = calculateNeededLocalCurrency(rate.getRate(), budgetPerCountry);
                    countryBudgets.add(new CountryBudgetDto(border, localCurrency, neededLocalCurrency));
                }
            }
        }

        return countryBudgets;
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
