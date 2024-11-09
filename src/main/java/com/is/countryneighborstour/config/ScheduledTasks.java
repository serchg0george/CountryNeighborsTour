package com.is.countryneighborstour.config;

import com.is.countryneighborstour.services.RenewingCurrencyRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final RenewingCurrencyRatesService renewingCurrencyRatesService;

    @Scheduled(cron = "0 00 00 * * ?")
    public void executeRenewingCurrencyRates() {
        renewingCurrencyRatesService.updateCurrencyRates();
    }
}
