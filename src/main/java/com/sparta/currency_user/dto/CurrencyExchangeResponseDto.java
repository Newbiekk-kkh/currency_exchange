package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyExchangeResponseDto {
    private long id;

    private BigDecimal amountAfterExchange;
    private CurrencyExchangeStatus status;

    public CurrencyExchangeResponseDto(long id, BigDecimal amountAfterExchange, CurrencyExchangeStatus status) {
        this.id = id;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public static CurrencyExchangeResponseDto toDto(CurrencyExchange currencyExchange) {
        return new CurrencyExchangeResponseDto(currencyExchange.getId(), currencyExchange.getAmountAfterExchange(), currencyExchange.getStatus());
    }
}
