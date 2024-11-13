package com.tecnica.exchange.infraestructure.webclient;

import com.tecnica.exchange.domain.model.ExchangeRateResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * WebClient component for fetching exchange rate data from an external API.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: ExchangeRateWebClient
 * </p>
 */
@Component
@Slf4j
public class ExchangeRateWebClient {

    private final WebClient webClient;

    /**
     * Constructs a WebClient for accessing the exchange rate API with the provided base URL.
     *
     * @param webClientBuilder The WebClient builder used to create the WebClient instance.
     * @param baseUrl          The base URL of the exchange rate API.
     */
    public ExchangeRateWebClient(WebClient.Builder webClientBuilder, @Value("${exchange.api-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    /**
     * Fetches the exchange rate for the specified base currency.
     * <p>
     * Uses CircuitBreaker to handle failures with a fallback method.
     *
     * @param base The base currency for which to fetch exchange rates.
     * @return A Mono containing the exchange rate response.
     */
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

    /**
     * Fallback method for the CircuitBreaker, called when the API call fails.
     *
     * @param throwable The exception thrown during the failed API call.
     * @return A Mono containing an error response.
     */
    public Mono<ExchangeRateResponse> fallbackMethod(Throwable throwable) {
        log.error("Fallback method called due to: {}", throwable.getMessage());
        throw new RuntimeException("Error calling exchange API: ", throwable);
    }
}