package com.sparta.currency_user.config;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@Profile("dev")
public class DataInitializer {
    @Autowired
    private CurrencyRepository currencyRepository;

    @PostConstruct
    public void init() {
        List<Currency> currencyList = currencyRepository.findAll();

        for (Currency currency : currencyList) {
            BigDecimal exchangeRate = currency.getExchangeRate();

            if (exchangeRate.compareTo(BigDecimal.ZERO) <= 0) {
                log.warn("환율이 0보다 작거나 같습니다.");
            } else if (exchangeRate.compareTo(new BigDecimal("10000")) > 0) {
                log.warn("환율이 지정된 범위를 벗어났습니다.");
            }
        }
    }

}