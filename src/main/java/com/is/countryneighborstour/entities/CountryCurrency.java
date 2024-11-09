package com.is.countryneighborstour.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing the association between a country and its currency.
 * Each instance of this class holds a country code and its corresponding currency code.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_country_currency")
public class CountryCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "currency_code")
    private String currencyCode;
}
