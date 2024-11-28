package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.CurrencyExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import com.sparta.currency_user.repository.CurrencyExchangeRepository;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public CurrencyExchangeResponseDto requestCurrencyExchange(Long userId, String email, Long amountInKrw, String currencyName) {
        User findUserById = userRepository.findByIdOrElseThrow(userId);
        User findUserByEmail = userRepository.findByEmailOrElseThrow(email);
        Currency findCurrency = currencyRepository.findByCurrencyNameOrElseThrow(currencyName);

        if (!findUserById.equals(findUserByEmail)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        BigDecimal amountAfterExchange = new BigDecimal("0");

        amountAfterExchange = BigDecimal.valueOf(amountInKrw).divide(findCurrency.getExchangeRate(), 2, RoundingMode.HALF_EVEN);

        CurrencyExchange currencyExchange = new CurrencyExchange(amountInKrw, amountAfterExchange, CurrencyExchangeStatus.NORMAL);
        currencyExchange.setUser(findUserByEmail);
        currencyExchange.setCurrency(findCurrency);

        currencyExchangeRepository.save(currencyExchange);

        return new CurrencyExchangeResponseDto(currencyExchange.getId(), currencyExchange.getAmountAfterExchange(), currencyExchange.getStatus());
    }

    public List<CurrencyExchangeResponseDto> findAllCurrencyExchangeByUser(Long userId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        List<CurrencyExchange> allCurrencyExchangeListByUser = currencyExchangeRepository.findAllByUser(findUser);

        return allCurrencyExchangeListByUser.stream().map(CurrencyExchangeResponseDto::toDto).toList();
    }
}
