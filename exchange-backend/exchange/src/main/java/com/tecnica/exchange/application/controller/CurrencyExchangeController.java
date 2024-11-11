package com.tecnica.exchange.application.controller;

import com.tecnica.exchange.application.service.CurrencyExchangeService;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.infraestructure.config.CurrencyProperties;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("api/exchange")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeServiceImpl;
    private final CurrencyProperties properties;

    @PostMapping("/convert")
    public Mono<ExchangeApiResponse> convertCurrency(@Valid @RequestBody ExchangeRequest request) {
        return currencyExchangeServiceImpl.convertCurrency(request);
    }

    @GetMapping("/records")
    public Flux<ExchangeApiResponse> getAllExchangeRecords() {

        return currencyExchangeServiceImpl.listQueryRecords();
    }

}