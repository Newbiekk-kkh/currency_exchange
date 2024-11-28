package com.sparta.currency_user.dto.currencyExchange;

import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import lombok.Getter;

@Getter
public class UpdateCurrencyExchangeRequestDto {
    private final CurrencyExchangeStatus status;

    public UpdateCurrencyExchangeRequestDto(CurrencyExchangeStatus status) {
        this.status = status;
    }
}
