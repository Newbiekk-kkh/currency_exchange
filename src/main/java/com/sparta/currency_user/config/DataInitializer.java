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
        // 달러와 엔화 데이터 추가
        currencyRepository.save(new Currency("USD", new BigDecimal("1400"), "$", 2)); // 달러
        currencyRepository.save(new Currency("JPY", new BigDecimal("9"), "円", 0)); // 엔화

        List<Currency> currencyList = currencyRepository.findAll();

        // 스프링이 구동될 때 통화 테이블에 있는 환율이 0이거나 음수이거나 지정된 범위를 벗어나는 경우
        // 유효하지 않은 값으로 간주하고 로그를 기록
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