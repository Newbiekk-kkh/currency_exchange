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

    /**
     * 환전 요청
     *
     * @param userId URL 경로 유저아이디
     * @param dto    CurrencyExchangeRequestDto
     * @return ResponseEntity<>(currencyExchangeResponseDto, HttpStatus.CREATED), 실패시 상황에 맞는 에러코드
     * @throws CurrencyExchangeException
     */
    @PostMapping
    public ResponseEntity<CurrencyExchangeResponseDto> requestCurrencyExchange(
            @PathVariable Long userId,
            @Validated @RequestBody CurrencyExchangeRequestDto dto
            ) throws CurrencyExchangeException {
        CurrencyExchangeResponseDto currencyExchangeResponseDto = currencyExchangeService.requestCurrencyExchange(
                userId,
                dto.getEmail(),
                dto.getAmountInKrw(),
                dto.getCurrencyName()
        );

        return new ResponseEntity<>(currencyExchangeResponseDto, HttpStatus.CREATED);
    }

    /**
     * 특정 회원이 요청한 환전 목록 보기
     *
     * @param userId URL 경로 유저아이디
     * @return ResponseEntity<>(allCurrencyExchangeListByUser, HttpStatus.OK), 실패시 상황에 맞는 에러코드
     * @throws CurrencyExchangeException
     */
    @GetMapping
    public ResponseEntity<List<CurrencyExchangeResponseDto>> findAllCurrencyExchangeByUser(
            @PathVariable Long userId
            ) throws CurrencyExchangeException {
        List<CurrencyExchangeResponseDto> allCurrencyExchangeListByUser = currencyExchangeService.findAllCurrencyExchangeByUser(userId);

        return new ResponseEntity<>(allCurrencyExchangeListByUser, HttpStatus.OK);
    }

    /**
     * 특정 유저가 요청한 환전의 총 횟수와, 총 금액
     *
     * @param userId URL 경로 유저아이디
     * @return ResponseEntity<>(totalCurrencyExchangeByUser, HttpStatus.OK), 실패시 상황에 맞는 에러코드
     */
    @GetMapping("/total")
    public ResponseEntity<List<TotalCurrencyExchangeByUserResponseDto>> findTotalCurrencyExchangeByUser(@PathVariable Long userId) {
        List<TotalCurrencyExchangeByUserResponseDto> totalCurrencyExchangeByUser = currencyExchangeService.findTotalCurrencyExchangeByUser(userId);

        return new ResponseEntity<>(totalCurrencyExchangeByUser, HttpStatus.OK);
    }

    /**
     * 환전 상태 업데이트 (정상 -> 취소)
     *
     * @param userId              URL 경로 유저아이디
     * @param currencyExchangesId 환전 요청 Id
     * @param dto                 CurrencyExchangeResponseDto
     * @return ResponseEntity<>(updatedCurrencyExchangeResponseDto, HttpStatus.OK), 실패시 상황에 맞는 에러코드
     * @throws CurrencyExchangeException
     */
    @PatchMapping("/{currencyExchangesId}")
    public ResponseEntity<CurrencyExchangeResponseDto> updateCurrencyExchangeStatus(
            @PathVariable("userId") Long userId,
            @PathVariable("currencyExchangesId") Long currencyExchangesId,
            @RequestBody UpdateCurrencyExchangeRequestDto dto
            ) throws CurrencyExchangeException {
        CurrencyExchangeResponseDto updatedCurrencyExchangeResponseDto = currencyExchangeService.updateCurrencyExchangeStatus(userId, currencyExchangesId, dto.getStatus());

        return new ResponseEntity<>(updatedCurrencyExchangeResponseDto, HttpStatus.OK);
    }
}
