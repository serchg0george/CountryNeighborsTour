package com.is.countryneighborstour.repositories;

import com.is.countryneighborstour.entities.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, Double> {
}
