package com.sparta.currency_user.dto;

import lombok.Getter;

@Getter
public class CurrencyExchangeRequestDto {
    private String email;
    private Long amountInKrw;
    private String currencyName;

    public CurrencyExchangeRequestDto(String email, Long amountInKrw, String currencyName) {
        this.email = email;
        this.amountInKrw = amountInKrw;
        this.currencyName = currencyName;
    }
}
