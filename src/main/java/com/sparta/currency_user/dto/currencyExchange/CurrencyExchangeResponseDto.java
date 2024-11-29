package com.sparta.currency_user.dto.currencyExchange;

import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import com.sparta.currency_user.service.CurrencyExchangeService;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyExchangeResponseDto {
    private long id;

    private String formattedAmount;
    private CurrencyExchangeStatus status;

    public CurrencyExchangeResponseDto(long id, String formattedAmount, CurrencyExchangeStatus status) {
        this.id = id;
        this.formattedAmount = formattedAmount;
        this.status = status;
    }

    public static CurrencyExchangeResponseDto toDto(CurrencyExchange currencyExchange) {
        return new CurrencyExchangeResponseDto(currencyExchange.getId(), currencyExchange.getFormattedAmount(), currencyExchange.getStatus());
    }
}
