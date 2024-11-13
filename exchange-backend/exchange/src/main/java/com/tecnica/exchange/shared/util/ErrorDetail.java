package com.tecnica.exchange.shared.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents detailed information about an error related to a specific field.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: ErrorDetail
 * </p>
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ErrorDetail {
    private String field;
    private String message;
}
