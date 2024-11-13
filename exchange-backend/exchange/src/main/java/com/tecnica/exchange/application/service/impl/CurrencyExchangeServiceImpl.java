package com.tecnica.exchange.application.service.impl;

import com.tecnica.exchange.application.service.CurrencyExchangeService;
import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.domain.repository.ExchangeRecordRepository;
import com.tecnica.exchange.infraestructure.adapter.ExchangeResponseAdapter;
import com.tecnica.exchange.infraestructure.webclient.ExchangeRateWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service implementation for handling currency exchange operations.
 * This service provides methods to convert currencies and list all exchange records.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: CurrencyExchangeServiceImpl
 * </p>
 *
 * @see CurrencyExchangeService
 * @see ExchangeRateWebClient
 * @see ExchangeRecordRepository
 * @see ExchangeResponseAdapter
 */
@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final ExchangeRateWebClient exchangeRateClient;
    private final ExchangeRecordRepository repository;
    private final ExchangeResponseAdapter adapter;

    /**
     * Converts currency based on the request's source currency and amount.
     * Saves the conversion record after retrieving the exchange rate.
     *
     * @param request the {@link ExchangeRequest} containing currency data
     * @return a {@link Mono} with the conversion result
     * @see ExchangeRateWebClient#getExchangeRate(String)
     * @see ExchangeRecordRepository#save(Object)
     */
    public Mono<ExchangeApiResponse> convertCurrency(ExchangeRequest request) {
        return exchangeRateClient.getExchangeRate(request.getSourceCurrency())
                .flatMap(response -> repository.save(adapter.convertExchangeRecordResponse(response, request)));
    }

    /**
     * Retrieves all exchange records from the repository.
     *
     * @return a {@link Flux} of all exchange records
     * @see ExchangeRecordRepository#findAll()
     */
    @Override
    public Flux<ExchangeApiResponse> listQueryRecords() {
        return repository.findAll();
    }
}
