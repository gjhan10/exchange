package com.tecnica.exchange.application.controller;

import com.tecnica.exchange.application.service.CurrencyExchangeService;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.domain.model.ExchangeResponse;
import com.tecnica.exchange.infraestructure.config.CurrencyProperties;
import com.tecnica.exchange.shared.util.ValidCurrencyUtil;
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
    public Mono<ExchangeResponse> convertCurrency(@Valid @RequestBody ExchangeRequest request) {
        ValidCurrencyUtil.validateCurrencies(properties.getValidCurrencies(), request);
        return currencyExchangeServiceImpl.convertCurrency(request);
    }

    @GetMapping("/records")
    public Flux<ExchangeResponse> getAllExchangeRecords() {

        return currencyExchangeServiceImpl.listQueryRecords();
    }

}