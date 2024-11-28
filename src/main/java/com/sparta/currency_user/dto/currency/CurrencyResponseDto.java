package com.sparta.currency_user.dto.currency;

import com.sparta.currency_user.entity.Currency;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyResponseDto {
    private Long id;

    private String currencyName;
    private BigDecimal exchangeRate;
    private String symbol;

    public CurrencyResponseDto(Currency currency) {
        this.id = currency.getId();
        this.currencyName = currency.getCurrencyName();
        this.exchangeRate = currency.getExchangeRate();
        this.symbol = currency.getSymbol();
    }

    public CurrencyResponseDto(Long id, String currencyName, BigDecimal exchangeRate, String symbol) {
        this.id = id;
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
    }

    public static CurrencyResponseDto toDto(Currency currency) {
        return new CurrencyResponseDto(
            currency.getId(),
            currency.getCurrencyName(),
            currency.getExchangeRate(),
            currency.getSymbol()
        );
    }
}
