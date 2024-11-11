package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.RatesDto;
import com.is.countryneighborstour.exceptions.CurrencyNotFoundException;
import com.is.countryneighborstour.repositories.ExchangeRateRepository;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {
    private MockWebServer mockWebServer;
    private CurrencyServiceImpl currencyService;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        String baseUrl = mockWebServer.url("/").toString();
        currencyService = new CurrencyServiceImpl(baseUrl, "test_api_key", new ExchangeRateServiceImpl(exchangeRateRepository));
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetAllRates() {
        String mockResponse = "{ \"rates\": {\"USD\": 1.12, \"GBP\": 0.85} }";
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        Flux<RatesDto> ratesFlux = currencyService.getAllRates("EUR");

        RatesDto ratesDto = ratesFlux.blockFirst();
        assertNotNull(ratesDto);
        assertEquals(new BigDecimal("1.12"), ratesDto.rates().get("USD"));
        assertEquals(new BigDecimal("0.85"), ratesDto.rates().get("GBP"));
    }

    @Test
    void testGetAllRatesCurrencyNotFound() {
        String mockResponse = "{ \"rates\": {}}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        Flux<RatesDto> ratesFlux = currencyService.getAllRates("XYZ");

        assertThrows(CurrencyNotFoundException.class, ratesFlux::blockFirst);
    }

    @Test
    void testGetAllRatesInvalidApiKey() {
        String mockResponse = "{ \"error\": { \"code\": 101, \"info\": \"Invalid API key\" }}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(401));

        Flux<RatesDto> ratesFlux = currencyService.getAllRates("EUR");

        assertThrows(RuntimeException.class, ratesFlux::blockFirst);
    }

    @Test
    void testGetAllRatesEmptyResponse() {
        String mockResponse = "{}";
        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        Flux<RatesDto> ratesFlux = currencyService.getAllRates("USD");

        assertNotNull(ratesFlux);
        assertThrows(CurrencyNotFoundException.class, ratesFlux::blockFirst);
    }

}