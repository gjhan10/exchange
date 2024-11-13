package com.tecnica.exchange.infraestructure.config;

import com.tecnica.exchange.shared.util.ValidCurrency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Custom validator for currency validation using configured valid currencies.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: CurrencyValidator
 * </p>
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@RequiredArgsConstructor
public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    /**
     * List of valid currencies configured in the application.
     */
    private List<String> validCurrencies;

    /**
     * Validates if the given currency is in the list of valid currencies.
     *
     * @param value   The currency value to validate.
     * @param context The context in which the constraint is being validated.
     * @return true if the currency is valid, false otherwise.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && validCurrencies.contains(value.toUpperCase());
    }

}