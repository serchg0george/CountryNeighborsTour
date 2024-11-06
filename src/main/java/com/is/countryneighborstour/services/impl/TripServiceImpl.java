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
    public String calculatePriceForCountry(String country, Integer totalBudget, Integer budgetPerCountry, String currency) {

        CountryInfoDto countryInfo = countriesService.getAllBorders(country);


        Integer neighborCount =  countryInfo.getBorders().size();
        Integer timesToVisit = totalBudget / (neighborCount * budgetPerCountry);
        Integer remainingBudget = totalBudget % (neighborCount * budgetPerCountry);

        return "Times to visit: " + timesToVisit + " Remaining Budget: " + remainingBudget + " " + currency;
    }

}
