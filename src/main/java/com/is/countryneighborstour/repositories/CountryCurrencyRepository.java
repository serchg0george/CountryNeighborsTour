package com.is.countryneighborstour.repositories;

import com.is.countryneighborstour.entities.CountryCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryCurrencyRepository extends JpaRepository<CountryCurrency, Long> {
    List<CountryCurrency> findByCountryCodeIn(List<String> countryCodes);
}
