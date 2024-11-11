package com.tecnica.exchange.domain.exceptions;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CurrencyValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {
    String message() default "Invalid currency. Accepted values are defined in application.yml";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}