package com.sparta.currency_user.entity;

import com.sparta.currency_user.enums.CurrencyExchangeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class CurrencyExchange extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long amountInKrw;

    @Column(nullable = false)
    private String formattedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyExchangeStatus status;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CurrencyExchange(Long amountInKrw, String formattedAmount, CurrencyExchangeStatus status) {
        this.status = status;
        this.amountInKrw = amountInKrw;
        this.formattedAmount = formattedAmount;
    }

    public void updateStatus(CurrencyExchangeStatus status) {
        this.status = status;
    }

    public CurrencyExchange() {
    }
}
