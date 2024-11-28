package com.sparta.currency_user.dto.currencyExchange;

import lombok.Getter;

@Getter
public class TotalCurrencyExchangeByUserResponseDto {
    private Long count;
    private Long totalAmountInKrw;

    public TotalCurrencyExchangeByUserResponseDto(Long count, Long totalAmountInKrw) {
        this.count = count;
        this.totalAmountInKrw = totalAmountInKrw;
    }
}
