package com.sparta.currency_user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CurrencyExchangeException extends Exception {
    private final CurrencyExchangeErrorCode errorCode;

    public CurrencyExchangeException(CurrencyExchangeErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
