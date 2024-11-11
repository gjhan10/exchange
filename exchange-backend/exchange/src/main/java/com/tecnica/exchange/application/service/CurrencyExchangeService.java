package com.tecnica.exchange.application.service;

import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyExchangeService {
    Mono<ExchangeApiResponse> convertCurrency(ExchangeRequest request);

    Flux<ExchangeApiResponse> listQueryRecords();
}
