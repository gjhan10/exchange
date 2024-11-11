package com.tecnica.exchange.infraestructure.webclient;

import com.tecnica.exchange.shared.dto.ExchangeRateResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ExchangeRateWebClient {

    private final WebClient webClient;

    public ExchangeRateWebClient(WebClient.Builder webClientBuilder, @Value("${exchange.api-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @CircuitBreaker(name = "get-exchange", fallbackMethod = "fallbackMethod")
    public Mono<ExchangeRateResponse> getExchangeRate(String base) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("base", base)
                        .build())
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .doOnSuccess(valid -> log.info("Type of change made successfully"))
                .doOnError(error -> log.error("Error when calling exchange rate API"));
    }

    public Mono<ExchangeRateResponse> fallbackMethod(Throwable throwable) {
        log.info("Fallback method called due to: " + throwable.getMessage());
        throw new RuntimeException("Error calling exchange API: ", throwable);
    }
}