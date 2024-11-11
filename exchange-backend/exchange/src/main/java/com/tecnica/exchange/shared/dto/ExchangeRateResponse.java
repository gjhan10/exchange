package com.tecnica.exchange.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExchangeRateResponse {
    private String base;
    private Map<String, Double> rates;

}
