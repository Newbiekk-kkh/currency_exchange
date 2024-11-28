package com.sparta.currency_user.entity;

import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CurrencyExchange extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long amountInKrw;
    private Long amountAfterExchange;
    private CurrencyExchangeStatus status;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CurrencyExchange(Long amountInKrw, Long amountAfterExchange, CurrencyExchangeStatus status) {
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public CurrencyExchange() {
    }
}
