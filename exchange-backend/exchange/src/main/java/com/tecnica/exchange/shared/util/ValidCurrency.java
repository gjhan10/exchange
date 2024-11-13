package com.tecnica.exchange.shared.util;

import com.tecnica.exchange.infraestructure.config.CurrencyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating currency fields.
 * This annotation ensures that the currency value is one of the valid currencies defined in the application's configuration.
 * <p>
 * The validation is performed by the {@link CurrencyValidator} class.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 *     @ValidCurrency
 *     private String sourceCurrency;
 * </pre>
 *
 * @author JJGF
 * @version 1.0
 * @since 2024-11-13
 */
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "Invalid currency. Accepted values are defined in application.yml";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}