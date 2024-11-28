package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.entity.User;
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

    default CurrencyExchange findByUserOrElseThrow(User user) {
        return findByUser(user).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));
    }
}
