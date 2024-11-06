package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.services.CountriesService;
import com.is.countryneighborstour.services.TripService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {

    private final CountriesService countriesService;

    @Override
    public String calculatePriceForCountry(String country, int totalBudget, int budgetPerCountry, String currency) {

        CountryInfoDto countryInfo = countriesService.getAllBorders(country);


        int neighborCount =  countryInfo.getBorders().size();
        int timesToVisit = totalBudget / (neighborCount * budgetPerCountry);
        int remainingBudget = totalBudget % (neighborCount * budgetPerCountry);

        return "Times to visit: " + timesToVisit + " Remaining Budget: " + remainingBudget + " " + currency;
    }

}
