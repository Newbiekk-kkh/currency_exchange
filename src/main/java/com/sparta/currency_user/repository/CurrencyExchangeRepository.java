package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.exception.CurrencyExchangeErrorCode;
import com.sparta.currency_user.exception.CurrencyExchangeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    Optional<CurrencyExchange> findByUser(User user);
    List<CurrencyExchange> findAllByUser(User user);

    default CurrencyExchange findByUserOrElseThrow(User user) throws CurrencyExchangeException {
        return findByUser(user).orElseThrow(()-> new CurrencyExchangeException(CurrencyExchangeErrorCode.USER_NOT_FOUND));
    }

    default CurrencyExchange findByIdOrElseThrow(Long id) throws CurrencyExchangeException {
        return findById(id).orElseThrow(()-> new CurrencyExchangeException(CurrencyExchangeErrorCode.CURRENCYEXCHANGE_NOT_FOUND));
    }
}
