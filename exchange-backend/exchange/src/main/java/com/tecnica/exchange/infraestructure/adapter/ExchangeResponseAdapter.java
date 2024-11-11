package com.tecnica.exchange.infraestructure.adapter;

import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.model.ExchangeRateResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeResponseAdapter {

    public ExchangeApiResponse convertExchangeRecordResponse(ExchangeRateResponse rateResponse, ExchangeRequest request) {

        BigDecimal exchangeRate = BigDecimal.valueOf(rateResponse.getRates().get(request.getTargetCurrency()));

        return ExchangeApiResponse.builder()
                .amount(request.getAmount())
                .sourceCurrency(request.getSourceCurrency())
                .targetCurrency(request.getTargetCurrency())
                .exchangeRate(exchangeRate)
                .convertedAmount(exchangeRate.multiply(request.getAmount()))
                .build();
    }
}
