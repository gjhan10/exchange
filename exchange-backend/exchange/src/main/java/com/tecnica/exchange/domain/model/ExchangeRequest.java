package com.tecnica.exchange.domain.model;

import com.tecnica.exchange.domain.exceptions.ValidCurrency;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequest {

    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;
    @ValidCurrency(message = "SourceCurrency invalid")
    private String sourceCurrency;
    @ValidCurrency(message = "TargetCurrency invalid")
    private String targetCurrency;
}
