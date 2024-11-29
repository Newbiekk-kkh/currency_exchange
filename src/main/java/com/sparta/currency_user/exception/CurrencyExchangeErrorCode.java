package com.sparta.currency_user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CurrencyExchangeErrorCode {
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR001", "이메일을 찾을 수 없습니다."),
    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR002", "고객 아이디를 찾을 수 없습니다."),
    CURRENCY_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR003", "통화 아이디를 찾을 수 없습니다."),
    CURRENCY_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR004", "통화를 찾을 수 없습니다."),
    CURRENCY_EXCHANGE_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR005", "환전 요청을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ERR006", "고객을 찾을 수 없습니다."),
    EMAIL_MISMATCH(HttpStatus.BAD_REQUEST, "ERR007", "이메일이 다릅니다."),
    USER_MISMATCH(HttpStatus.BAD_REQUEST, "ERR008", "환전요청을 한 고객이 다릅니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
