package com.tecnica.exchange.application.service.impl;

import com.tecnica.exchange.application.service.CurrencyExchangeService;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.repository.ExchangeRecordRepository;
import com.tecnica.exchange.infraestructure.adapter.ExchangeResponseAdapter;
import com.tecnica.exchange.infraestructure.webclient.ExchangeRateWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final ExchangeRateWebClient exchangeRateClient;
    private final ExchangeRecordRepository repository;
    private final ExchangeResponseAdapter adapter;

    public Mono<ExchangeApiResponse> convertCurrency(ExchangeRequest request) {
        return exchangeRateClient.getExchangeRate(request.getSourceCurrency())
                .flatMap(response -> repository.save(adapter.convertExchangeRecordResponse(response, request)));
    }

    @Override
    public Flux<ExchangeApiResponse> listQueryRecords() {
        return repository.findAll();
    }
}
