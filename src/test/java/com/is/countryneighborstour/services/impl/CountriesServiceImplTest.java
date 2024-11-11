package com.is.countryneighborstour.services.impl;

import com.is.countryneighborstour.dto.CountryInfoDto;
import com.is.countryneighborstour.exceptions.TripCountryBadRequestException;
import com.is.countryneighborstour.exceptions.TripCountryNotFoundException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CountriesServiceImplTest {

    private final String restCountriesApiAddress = "https://restcountries.com/v3.1/alpha/";
    private MockWebServer mockWebServer;
    private CountriesServiceImpl countriesService;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        countriesService = new CountriesServiceImpl(restCountriesApiAddress);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetAllBorders_success() {

        String countryCode = "USA";
        String mockResponse = "{ \"borders\": [\"CAN\", \"MEX\"] }";

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json")
                .setResponseCode(HttpStatus.OK.value()));

        CountryInfoDto result = countriesService.getAllBorders(countryCode);

        assertNotNull(result);
        assertEquals(2, result.borders().size());
        assertEquals("CAN", result.borders().get(0));
        assertEquals("MEX", result.borders().get(1));
    }

    @Test
    void testGetAllBorders_notFound() {

        String countryCode = "XYZ";

        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.NOT_FOUND.value()));

        assertThrows(TripCountryNotFoundException.class, () -> countriesService.getAllBorders(countryCode));
    }

    @Test
    void testGetAllBorders_badRequest() {

        String countryCode = "";

        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpStatus.BAD_REQUEST.value()));

        assertThrows(TripCountryBadRequestException.class, () -> countriesService.getAllBorders(countryCode));
    }
}
