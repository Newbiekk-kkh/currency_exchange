package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.currencyExchange.TotalCurrencyExchangeByUserResponseDto;
import com.sparta.currency_user.dto.currencyExchange.CurrencyExchangeRequestDto;
import com.sparta.currency_user.dto.currencyExchange.CurrencyExchangeResponseDto;
import com.sparta.currency_user.dto.currencyExchange.UpdateCurrencyExchangeRequestDto;
import com.sparta.currency_user.exception.CurrencyExchangeException;
import com.sparta.currency_user.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
            @Validated @RequestBody CurrencyExchangeRequestDto dto)
            throws CurrencyExchangeException {
        CurrencyExchangeResponseDto currencyExchangeResponseDto = currencyExchangeService.requestCurrencyExchange(userId, dto.getEmail(), dto.getAmountInKrw(), dto.getCurrencyName());

        return new ResponseEntity<>(currencyExchangeResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CurrencyExchangeResponseDto>> findAllCurrencyExchangeByUser(
            @PathVariable Long userId)
            throws CurrencyExchangeException {
        List<CurrencyExchangeResponseDto> allCurrencyExchangeListByUser = currencyExchangeService.findAllCurrencyExchangeByUser(userId);

        return new ResponseEntity<>(allCurrencyExchangeListByUser, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<List<TotalCurrencyExchangeByUserResponseDto>> findTotalCurrencyExchangeByUser(@PathVariable Long userId) {
        List<TotalCurrencyExchangeByUserResponseDto> totalCurrencyExchangeByUser = currencyExchangeService.findTotalCurrencyExchangeByUser(userId);

        return new ResponseEntity<>(totalCurrencyExchangeByUser, HttpStatus.OK);
    }

    @PatchMapping("/{currencyExchangesId}")
    public ResponseEntity<CurrencyExchangeResponseDto> updateCurrencyExchangeStatus(
            @PathVariable("userId") Long userId,
            @PathVariable("currencyExchangesId") Long currencyExchangesId,
            @RequestBody UpdateCurrencyExchangeRequestDto dto)
            throws CurrencyExchangeException {
        CurrencyExchangeResponseDto updatedCurrencyExchangeResponseDto = currencyExchangeService.updateCurrencyExchangeStatus(userId, currencyExchangesId, dto.getStatus());

        return new ResponseEntity<>(updatedCurrencyExchangeResponseDto, HttpStatus.OK);
    }
}
