package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.currencyExchange.CurrencyExchangeResponseDto;
import com.sparta.currency_user.dto.currencyExchange.TotalCurrencyExchangeByUserResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import com.sparta.currency_user.exception.CurrencyExchangeErrorCode;
import com.sparta.currency_user.exception.CurrencyExchangeException;
import com.sparta.currency_user.repository.CurrencyExchangeRepository;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    // 환전 요청 로직
    @Transactional
    public CurrencyExchangeResponseDto requestCurrencyExchange(Long userId, String email, Long amountInKrw, String currencyName) throws CurrencyExchangeException {
        User findUserById = userRepository.findByIdOrElseThrow(userId);
        Currency findCurrency = currencyRepository.findByCurrencyNameOrElseThrow(currencyName);

        if (!findUserById.getEmail().equals(email)) {
            throw new CurrencyExchangeException(CurrencyExchangeErrorCode.EMAIL_MISMATCH);
        }

        BigDecimal amountAfterExchange = new BigDecimal("0");
        amountAfterExchange = BigDecimal.valueOf(amountInKrw).divide(findCurrency.getExchangeRate(), findCurrency.getScale(), RoundingMode.HALF_EVEN);
        String formattedAmount = formatAmount(amountAfterExchange, findCurrency.getSymbol());
        
        CurrencyExchange currencyExchange = new CurrencyExchange(amountInKrw, formattedAmount, CurrencyExchangeStatus.NORMAL);
        currencyExchange.setUser(findUserById);
        currencyExchange.setCurrency(findCurrency);

        currencyExchangeRepository.save(currencyExchange);

        return new CurrencyExchangeResponseDto(currencyExchange.getId(), currencyExchange.getFormattedAmount(), currencyExchange.getStatus());
    }

    // 특정 고객의 환전 요청 리스트 조회 로직
    public List<CurrencyExchangeResponseDto> findAllCurrencyExchangeByUser(Long userId) throws CurrencyExchangeException {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        List<CurrencyExchange> allCurrencyExchangeListByUser = currencyExchangeRepository.findAllByUser(findUser);

        return allCurrencyExchangeListByUser.stream().map(CurrencyExchangeResponseDto::toDto).toList();
    }

    // 특정 고객의 총 환전 횟수 및 총 환전 금액 조회 로직
    public List<TotalCurrencyExchangeByUserResponseDto> findTotalCurrencyExchangeByUser(Long userId) {
        List<TotalCurrencyExchangeByUserResponseDto> totalCurrencyExchange = currencyExchangeRepository.findTotalCurrencyExchangeByUser(userId);
        return totalCurrencyExchange;
    }

    // 환전 요청의 상태 업데이트 로직
    @Transactional
    public CurrencyExchangeResponseDto updateCurrencyExchangeStatus(Long userId, Long currencyExchangeId, CurrencyExchangeStatus status) throws CurrencyExchangeException {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        CurrencyExchange findCurrencyExchange = currencyExchangeRepository.findByIdOrElseThrow(currencyExchangeId);

        if (!findCurrencyExchange.getUser().equals(findUser)) {
            throw new CurrencyExchangeException(CurrencyExchangeErrorCode.USER_MISMATCH);
        }

        findCurrencyExchange.updateStatus(status);

        return new CurrencyExchangeResponseDto(findCurrencyExchange.getId(), findCurrencyExchange.getFormattedAmount(), findCurrencyExchange.getStatus());
    }

    // 통화별 출력 형식 적용
    public static String formatAmount(BigDecimal amountAfterExchange, String symbol) {
        return amountAfterExchange + symbol;
    }
}
