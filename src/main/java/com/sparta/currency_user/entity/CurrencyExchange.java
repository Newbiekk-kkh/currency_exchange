package com.sparta.currency_user.entity;

import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
public class CurrencyExchange extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long amountInKrw;

    @Column(nullable = false)
    private BigDecimal amountAfterExchange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyExchangeStatus status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CurrencyExchange(Long amountInKrw, BigDecimal amountAfterExchange, CurrencyExchangeStatus status) {
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
    }

    public CurrencyExchange() {
    }
}
