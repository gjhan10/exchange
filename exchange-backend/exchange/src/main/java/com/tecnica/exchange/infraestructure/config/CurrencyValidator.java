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

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@RequiredArgsConstructor
public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {


    private List<String> validCurrencies;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && validCurrencies.contains(value.toUpperCase());
    }

}