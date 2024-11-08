package com.is.countryneighborstour.repositories;

import com.is.countryneighborstour.entities.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Repository interface for accessing and managing exchange rate data in the database.
 * Extends JpaRepository to provide CRUD operations for the ExchangeRates entity.
 */

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, Long> {
    ExchangeRates findByBaseCurrencyAndTargetCurrencyAndDate(String baseCurrency, String targetCurrency, LocalDate date);
}
