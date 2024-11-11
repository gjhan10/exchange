package com.tecnica.exchange.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {
    private String field;
    private String message;
}
