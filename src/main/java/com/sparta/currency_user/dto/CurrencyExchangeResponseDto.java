package com.sparta.currency_user.dto;

import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import lombok.Getter;

@Getter
public class CurrencyExchangeResponseDto {
    private long id;

    private Long amountAfterExchange;
    private CurrencyExchangeStatus status;

    public CurrencyExchangeResponseDto(long id, Long amountAfterExchange, CurrencyExchangeStatus status) {
        this.id = id;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }
}
