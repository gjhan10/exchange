package com.tecnica.exchange.infraestructure.adapter;

import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.model.ExchangeRateResponse;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Adapter class for converting exchange rate responses into exchange API response format.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: ExchangeResponseAdapter
 * </p>
 */
@Component
public class ExchangeResponseAdapter {

    /**
     * Converts exchange rate response and request to API response.
     *
     * @param rateResponse The exchange rate response.
     * @param request      The exchange request.
     * @return The converted API response.
     */
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
