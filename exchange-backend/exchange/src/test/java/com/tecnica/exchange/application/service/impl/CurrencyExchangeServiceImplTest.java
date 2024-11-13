package com.tecnica.exchange.application.service.impl;


import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.repository.ExchangeRecordRepository;
import com.tecnica.exchange.infraestructure.adapter.ExchangeResponseAdapter;
import com.tecnica.exchange.infraestructure.webclient.ExchangeRateWebClient;
import com.tecnica.exchange.utiltest.UtilTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link CurrencyExchangeServiceImpl}.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: CurrencyExchangeServiceImplTest
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class CurrencyExchangeServiceImplTest {

    @InjectMocks
    CurrencyExchangeServiceImpl service;

    @Mock
    ExchangeRateWebClient exchangeRateWebClient;

    @Mock
    ExchangeRecordRepository repository;

    @Mock
    ExchangeResponseAdapter adapter;


    @Test
    @DisplayName("Success when calling the exchange external api")
    void successWhenCallingTheExchangeExternalApi() {
        when(exchangeRateWebClient.getExchangeRate(any())).thenReturn(Mono.just(UtilTest.getExchangeRateResponseMock()));
        when(repository.save(any())).thenReturn(Mono.just(UtilTest.getExchangeApiResponseMock()));

        Mono<ExchangeApiResponse> result = service.convertCurrency(UtilTest.getExchangeRequestMock());

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @DisplayName("List all exchange records successfully")
    void listQueryRecordsSuccess() {
        ExchangeApiResponse response1 = UtilTest.getExchangeApiResponseMock();
        ExchangeApiResponse response2 = UtilTest.getExchangeApiResponseFluxMock();

        when(repository.findAll()).thenReturn(Flux.fromIterable(asList(response1, response2)));

        StepVerifier.create(service.listQueryRecords())
                .expectNext(response1)
                .expectNext(response2)
                .verifyComplete();
    }
}