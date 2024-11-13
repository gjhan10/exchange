package com.tecnica.exchange.application.controller;

import com.tecnica.exchange.application.service.CurrencyExchangeService;
import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.infraestructure.config.CurrencyProperties;
import com.tecnica.exchange.utiltest.UtilTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link CurrencyExchangeController}.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: CurrencyExchangeControllerTest
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class CurrencyExchangeControllerTest {

    @InjectMocks
    private CurrencyExchangeController controller;

    @Mock
    private CurrencyExchangeService currencyExchangeServiceImpl;

    @Mock
    private CurrencyProperties properties;

    @Test
    @DisplayName("Convert currency successfully")
    void convertCurrencySuccess() {

        ExchangeApiResponse expectedResponse = UtilTest.getExchangeApiResponseMock();
        when(currencyExchangeServiceImpl.convertCurrency(any())).thenReturn(Mono.just(expectedResponse));

        Mono<ResponseEntity<ExchangeApiResponse>> result = controller.convertCurrency(UtilTest.getExchangeRequestMock());

        StepVerifier.create(result)
                .assertNext(responseEntity -> {
                    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                    assertEquals(expectedResponse, responseEntity.getBody());
                })
                .expectComplete()
                .verify();
    }


    @Test
    @DisplayName("Get all exchange records with HTTP status 200")
    void getAllExchangeRecordsSuccess() {
        ExchangeApiResponse response1 = UtilTest.getExchangeApiResponseMock();
        ExchangeApiResponse response2 = UtilTest.getExchangeApiResponseFluxMock();

        when(currencyExchangeServiceImpl.listQueryRecords()).thenReturn(Flux.just(response1, response2));

        Flux<ResponseEntity<ExchangeApiResponse>> result = controller.getAllExchangeRecords();

        StepVerifier.create(result)
                .assertNext(responseEntity -> {
                    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                    assertEquals(response1, responseEntity.getBody());
                })
                .assertNext(responseEntity -> {
                    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
                    assertEquals(response2, responseEntity.getBody());
                })
                .expectComplete()
                .verify();
    }
}