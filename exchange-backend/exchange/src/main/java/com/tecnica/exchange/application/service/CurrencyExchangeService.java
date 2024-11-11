package com.tecnica.exchange.application.service;

import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.domain.model.ExchangeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyExchangeService {
    Mono<ExchangeResponse> convertCurrency(ExchangeRequest request);

    Flux<ExchangeResponse> listQueryRecords();
}
