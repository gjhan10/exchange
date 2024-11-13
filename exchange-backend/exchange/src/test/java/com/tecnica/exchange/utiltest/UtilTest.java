package com.tecnica.exchange.utiltest;

import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import com.tecnica.exchange.domain.model.ExchangeRateResponse;
import com.tecnica.exchange.domain.model.ExchangeRequest;
import com.tecnica.exchange.shared.util.ApiException;
import com.tecnica.exchange.shared.util.ErrorDetail;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;
import java.util.*;

/**
 * Utility class for creating mock objects and test data used in unit tests.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: UtilTest
 * </p>
 */
public class UtilTest {

    /**
     * Creates a mock ExchangeRateResponse with a USD to PEN rate.
     *
     * @return A mocked ExchangeRateResponse object.
     */
    public static ExchangeRateResponse getExchangeRateResponseMock() {

        Map<String, Double> rate = new HashMap<>();
        rate.put("USD", 3.79);
        return ExchangeRateResponse.builder()
                .base("USD")
                .rates(rate)
                .build();
    }

    /**
     * Creates a mock ExchangeApiResponse with a conversion from USD to PEN.
     *
     * @return A mocked ExchangeApiResponse object.
     */
    public static ExchangeApiResponse getExchangeApiResponseMock() {

        return ExchangeApiResponse.builder()
                .amount(new BigDecimal(1))
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .exchangeRate(new BigDecimal("3.79"))
                .convertedAmount(new BigDecimal("3.79"))
                .build();
    }

    /**
     * Creates a mock ExchangeApiResponse for a flux scenario (larger conversion).
     *
     * @return A mocked ExchangeApiResponse object.
     */
    public static ExchangeApiResponse getExchangeApiResponseFluxMock() {

        return ExchangeApiResponse.builder()
                .amount(new BigDecimal(2))
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .exchangeRate(new BigDecimal("3.79"))
                .convertedAmount(new BigDecimal("7.58"))
                .build();
    }

    /**
     * Creates a mock ExchangeRequest for currency conversion.
     *
     * @return A mocked ExchangeRequest object.
     */
    public static ExchangeRequest getExchangeRequestMock() {

        return ExchangeRequest.builder()
                .amount(new BigDecimal(1))
                .sourceCurrency("USD")
                .targetCurrency("PEN")
                .build();
    }

    /**
     * Creates a list of mock field errors for validation testing.
     *
     * @return A list of mocked FieldError objects.
     */
    public static List<FieldError> getListFieldErrorsMock() {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("objectName", "field1", "must not be null"));
        fieldErrors.add(new FieldError("objectName", "field2", "size must be between 1 and 10"));

        return fieldErrors;
    }

    /**
     * Creates a mock ApiException for WebExchangeBindException.
     *
     * @return A mocked ApiException for binding validation errors.
     */
    public static ApiException getApiExceptionForWebExchangeBindExceptionMock() {
        return ApiException.builder()
                .code("ER0001")
                .description("Invalid Request")
                .errorDetails(List.of(
                        new ErrorDetail("field1", "must not be null"),
                        new ErrorDetail("field2", "size must be between 1 and 10")
                ))
                .build();
    }

    /**
     * Creates a mock ApiException for ConstraintViolationException.
     *
     * @return A mocked ApiException for constraint violations.
     */
    public static ApiException getApiExceptionForConstraintViolationExceptionMock() {
        return ApiException.builder()
                .code("ER0001")
                .description("Invalid Request")
                .errorDetails(List.of(
                        new ErrorDetail("field1", "must not be blank"),
                        new ErrorDetail("field2", "must be positive")
                ))
                .build();
    }

    /**
     * Creates a mock ApiException for a generic exception scenario.
     *
     * @return A mocked ApiException for a generic error.
     */
    public static ApiException getApiExceptionForGenericExceptionMock() {
        return new ApiException(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                Collections.singletonList(new ErrorDetail("unknown", "Unexpected error"))
        );
    }

}
