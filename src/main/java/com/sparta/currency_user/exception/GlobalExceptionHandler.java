package com.sparta.currency_user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CurrencyExchangeException.class)
    public ResponseEntity<Map<String, String>> handleCurrencyExchangeException(CurrencyExchangeException e) {

        Map<String, String> response = new HashMap<>();
        response.put("errorMessage", e.getErrorCode().getMessage());
        response.put("errorCode", e.getErrorCode().getErrorCode());

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(response);
    }
}
