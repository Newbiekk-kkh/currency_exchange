package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.CurrencyExchangeRequestDto;
import com.sparta.currency_user.dto.CurrencyExchangeResponseDto;
import com.sparta.currency_user.dto.CurrencyResponseDto;
import com.sparta.currency_user.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/{userId}/currencyExchanges")
public class CurrencyExchangeController {
    private final CurrencyExchangeService currencyExchangeService;

    @PostMapping
    public ResponseEntity<CurrencyExchangeResponseDto> requestCurrencyExchange(
            @PathVariable Long userId,
            @RequestBody CurrencyExchangeRequestDto dto) {
        CurrencyExchangeResponseDto currencyExchangeResponseDto = currencyExchangeService.requestCurrencyExchange(userId, dto.getEmail(), dto.getAmountInKrw(), dto.getCurrencyName());

        return new ResponseEntity<>(currencyExchangeResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CurrencyExchangeResponseDto>> findAllCurrencyExchangeByUser(@PathVariable Long userId) {
        List<CurrencyExchangeResponseDto> allCurrencyExchangeListByUser = currencyExchangeService.findAllCurrencyExchangeByUser(userId);

        return new ResponseEntity<>(allCurrencyExchangeListByUser, HttpStatus.OK);
    }
}
