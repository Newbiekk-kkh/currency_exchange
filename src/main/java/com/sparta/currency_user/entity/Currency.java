package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import com.sparta.currency_user.entity.BaseEntity;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
public class    Currency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String currencyName;

    @Column(nullable = false)
    private BigDecimal exchangeRate;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private int scale;

    public Currency(String currencyName, BigDecimal exchangeRate, String symbol, int scale) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
        this.scale = scale;
    }

    public Currency() {}
}
