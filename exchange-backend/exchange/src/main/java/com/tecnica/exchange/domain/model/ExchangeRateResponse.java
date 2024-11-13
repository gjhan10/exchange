package com.tecnica.exchange.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * Represents the response containing the exchange rates for a base currency.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: ExchangeRateResponse
 * </p>
 */
@Getter
@Setter
@Builder
public class ExchangeRateResponse {
    private String base;
    private Map<String, Double> rates;

}
