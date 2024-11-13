package com.tecnica.exchange.infraestructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for application properties related to currency validation.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: CurrencyProperties
 * </p>
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class CurrencyProperties {

    /**
     * List of valid currencies configured in the application.
     */
    private List<String> validCurrencies;

}