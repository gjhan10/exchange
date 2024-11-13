package com.tecnica.exchange.application.service;

import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for currency exchange operations.
 * Provides methods for converting currency and retrieving exchange records.
 * <p>
 * This interface defines two core operations:
 * 1. Converting a specific amount of currency based on exchange rates.
 * 2. Retrieving a list of all past exchange records.
 * </p>
 *
 * @author JJGF
 * @version 1.0
 * @since 2024-11-13
 */
public interface CurrencyExchangeService {
    Mono<ExchangeApiResponse> convertCurrency(ExchangeRequest request);

    Flux<ExchangeApiResponse> listQueryRecords();
}
