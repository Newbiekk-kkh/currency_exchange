package com.sparta.currency_user.dto.currencyExchange;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CurrencyExchangeRequestDto {
    @NotBlank(message = "이메일은 빈칸으로 제출할 수 없습니다.")
    private String email;

    @NotNull
    @Positive
    private Long amountInKrw;

    @NotBlank(message = "통화는 빈칸으로 제출할 수 없습니다.")
    private String currencyName;

    public CurrencyExchangeRequestDto(String email, Long amountInKrw, String currencyName) {
        this.email = email;
        this.amountInKrw = amountInKrw;
        this.currencyName = currencyName;
    }
}
