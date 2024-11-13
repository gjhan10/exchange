package com.tecnica.exchange.shared.util;

import lombok.*;

import java.util.List;

/**
 * Represents an API exception containing error details for response handling.
 * <p>
 * Author: JJGF
 * Date: 2024-11-13
 * Class: ApiException
 * </p>
 */
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class ApiException {

    private String code;
    private String description;
    private List<ErrorDetail> errorDetails;

}

