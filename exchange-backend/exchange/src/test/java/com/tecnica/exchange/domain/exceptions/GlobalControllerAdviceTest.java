package com.tecnica.exchange.domain.exceptions;

import com.tecnica.exchange.shared.util.ApiException;
import com.tecnica.exchange.utiltest.UtilTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link GlobalControllerAdvice}.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: GlobalControllerAdviceTest
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class GlobalControllerAdviceTest {

    private final GlobalControllerAdvice advice = new GlobalControllerAdvice();


    @Test
    @DisplayName("Handle WebExchangeBindException with HTTP 400")
    void handleWebExchangeBindException() {

        WebExchangeBindException ex = mock(WebExchangeBindException.class);

        when(ex.getFieldErrors()).thenReturn(UtilTest.getListFieldErrorsMock());

        Mono<ResponseEntity<ApiException>> result = advice.handleValidationException(ex);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

                    ApiException expected = UtilTest.getApiExceptionForWebExchangeBindExceptionMock();
                    ApiException actual = response.getBody();

                    assertEquals(expected, actual);
                })
                .expectComplete()
                .verify();
    }


    @Test
    @DisplayName("Handle generic Exception with HTTP 500")
    void handleGenericException() {
        Exception ex = new Exception("Unexpected error");

        Mono<ResponseEntity<ApiException>> result = advice.handleGenericException(ex);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

                    ApiException expected = UtilTest.getApiExceptionForGenericExceptionMock();
                    ApiException actual = response.getBody();

                    assertEquals(expected, actual);
                })
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Handle ConstraintViolationException with HTTP 400")
    void handleConstraintViolationException() {
        ConstraintViolationException ex = mock(ConstraintViolationException.class);
        Set<ConstraintViolation<?>> violations = Set.of(
                createConstraintViolation("field1", "must not be blank"),
                createConstraintViolation("field2", "must be positive")
        );

        when(ex.getConstraintViolations()).thenReturn(violations);
        Mono<ResponseEntity<ApiException>> result = advice.handleValidationException(ex);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

                    ApiException expected = UtilTest.getApiExceptionForConstraintViolationExceptionMock();
                    ApiException actual = response.getBody();

                    assertEquals(expected, actual);
                })
                .expectComplete();
    }

    private ConstraintViolation<?> createConstraintViolation(String field, String message) {
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn(field);
        when(violation.getPropertyPath()).thenReturn(path);
        when(violation.getMessage()).thenReturn(message);
        return violation;
    }


}