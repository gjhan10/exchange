package com.tecnica.exchange.shared.util;

import com.tecnica.exchange.domain.model.ExchangeRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidCurrencyUtil {


    public static void validateCurrencies(List<String> currencyValues, ExchangeRequest request) {
        Optional.of(request.getSourceCurrency())
                .filter(currencyValues::contains)
                .flatMap(source -> Optional.of(request.getTargetCurrency())
                        .filter(currencyValues::contains))
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency code in request"));

    }
}


