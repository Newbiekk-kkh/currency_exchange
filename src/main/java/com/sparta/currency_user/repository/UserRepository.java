package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.exception.CurrencyExchangeErrorCode;
import com.sparta.currency_user.exception.CurrencyExchangeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User findByEmailOrElseThrow(String email) throws CurrencyExchangeException {
        return findByEmail(email).orElseThrow(()-> new CurrencyExchangeException(CurrencyExchangeErrorCode.EMAIL_NOT_FOUND));
    }

    default User findByIdOrElseThrow(Long id) throws CurrencyExchangeException {
       return findById(id).orElseThrow(()-> new CurrencyExchangeException(CurrencyExchangeErrorCode.USER_ID_NOT_FOUND));
    }
}
