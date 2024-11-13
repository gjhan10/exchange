package com.tecnica.exchange.domain.model;

import com.tecnica.exchange.shared.util.ValidCurrency;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Represents a request for currency exchange with details on the amount and currencies involved.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: ExchangeRequest
 * </p>
 */
@Getter
@Builder
@AllArgsConstructor
public class ExchangeRequest {

    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;
    @ValidCurrency(message = "SourceCurrency invalid")
    private String sourceCurrency;
    @ValidCurrency(message = "TargetCurrency invalid")
    private String targetCurrency;
}
