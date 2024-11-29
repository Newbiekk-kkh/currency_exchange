package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.currencyExchange.TotalCurrencyExchangeByUserResponseDto;
import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.exception.CurrencyExchangeErrorCode;
import com.sparta.currency_user.exception.CurrencyExchangeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    // 유저로 환전 요청 찾기
    Optional<CurrencyExchange> findByUser(User user);

    // 특정 유저의 모든 환정 요청 찾기
    List<CurrencyExchange> findAllByUser(User user);

    // 유저로 환전 요청 찾기
    default CurrencyExchange findByUserOrElseThrow(User user) throws CurrencyExchangeException {
        return findByUser(user).orElseThrow(() -> new CurrencyExchangeException(CurrencyExchangeErrorCode.USER_NOT_FOUND));
    }

    // 환전 요청 ID로 환전 요청 찾기
    default CurrencyExchange findByIdOrElseThrow(Long id) throws CurrencyExchangeException {
        return findById(id).orElseThrow(() -> new CurrencyExchangeException(CurrencyExchangeErrorCode.CURRENCY_EXCHANGE_NOT_FOUND));
    }

    // JPQL 을 사용한 특정 유저의 환전 요청 총 횟수 및 총 금액 보기
    @Query(" select new com.sparta.currency_user.dto.currencyExchange.TotalCurrencyExchangeByUserResponseDto ( " +
            " count(ce), sum(ce.amountInKrw)) " +
            " from CurrencyExchange ce " +
            " where ce.user.id = :userId " +
            " group by ce.user.id ")
    List<TotalCurrencyExchangeByUserResponseDto> findTotalCurrencyExchangeByUser(@Param("userId") Long userId);
}

