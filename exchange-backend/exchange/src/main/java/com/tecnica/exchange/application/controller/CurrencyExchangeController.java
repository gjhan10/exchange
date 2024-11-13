package com.tecnica.exchange.application.controller;

import com.tecnica.exchange.application.service.CurrencyExchangeService;
import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller for managing currency exchange operations.
 * Provides endpoints for converting currencies and retrieving exchange records.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: CurrencyExchangeController
 * </p>
 *
 * @see CurrencyExchangeService
 */
@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("api/exchange")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeServiceImpl;

    /**
     * Converts a given amount of currency from source to target currency.
     * This endpoint processes the conversion based on the provided request details.
     *
     * @param request the {@link ExchangeRequest} containing source and target currency data along with amount
     * @return a {@link Mono} containing a {@link ResponseEntity} with the conversion result
     * @see CurrencyExchangeService#convertCurrency(ExchangeRequest)
     */
    @PostMapping("/convert")
    public Mono<ResponseEntity<ExchangeApiResponse>> convertCurrency(@Valid @RequestBody ExchangeRequest request) {
        return currencyExchangeServiceImpl.convertCurrency(request)
                .map(ResponseEntity::ok);
    }

    /**
     * Retrieves all exchange records.
     * This endpoint returns a list of past exchange transactions.
     *
     * @return a {@link Flux} containing a {@link ResponseEntity} with the exchange records
     * @see CurrencyExchangeService#listQueryRecords()
     */
    @GetMapping("/records")
    public Flux<ResponseEntity<ExchangeApiResponse>> getAllExchangeRecords() {

        return currencyExchangeServiceImpl.listQueryRecords()
                .map(ResponseEntity::ok);
    }

}