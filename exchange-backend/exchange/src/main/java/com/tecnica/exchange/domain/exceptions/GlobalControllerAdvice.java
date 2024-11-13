package com.tecnica.exchange.domain.exceptions;

import com.tecnica.exchange.shared.util.ApiException;
import com.tecnica.exchange.shared.util.ErrorDetail;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * Global exception handler for handling validation and generic exceptions.
 * Provides centralized exception handling for various errors in the application.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: GlobalControllerAdvice
 * </p>
 *
 * @see ApiException
 * @see ErrorDetail
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    /**
     * Handles validation exceptions (e.g., field errors, constraint violations).
     * Returns a detailed error response with HTTP 400.
     *
     * @param ex the exception thrown
     * @return a {@link Mono} with a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler({WebExchangeBindException.class, ConstraintViolationException.class})
    public Mono<ResponseEntity<ApiException>> handleValidationException(Exception ex) {

        List<ErrorDetail> errorDetails = ex instanceof WebExchangeBindException webEx
                ? webEx.getFieldErrors().stream()
                .map(fieldError -> new ErrorDetail(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList()
                : ((ConstraintViolationException) ex).getConstraintViolations().stream()
                .map(violation -> new ErrorDetail(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();


        ApiException apiException = ApiException.builder()
                .code("ER0001")
                .description("Invalid Request")
                .errorDetails(errorDetails)
                .build();

        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiException));
    }

    /**
     * Handles generic exceptions and returns a 500 error response.
     *
     * @param ex the exception thrown
     * @return a {@link Mono} with a {@link ResponseEntity} containing the error details
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiException>> handleGenericException(Exception ex) {
        ErrorDetail errorDetail = new ErrorDetail("unknown", ex.getMessage());

        ApiException apiException = new ApiException(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                Collections.singletonList(errorDetail)
        );

        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiException));
    }


}

