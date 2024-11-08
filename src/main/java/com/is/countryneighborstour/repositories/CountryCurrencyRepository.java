package com.is.countryneighborstour.repositories;

import com.is.countryneighborstour.entities.CountryCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryCurrencyRepository extends JpaRepository<CountryCurrency, Long> {
    CountryCurrency findByCountryCode(String countryCode);
}
