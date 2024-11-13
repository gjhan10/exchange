package com.tecnica.exchange.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * Represents a record of a currency exchange transaction.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: ExchangeApiResponse
 * </p>
 */
@Table("exchange_record")
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ExchangeApiResponse {
    @Id
    private Long id;
    private BigDecimal amount;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;

}