package com.tecnica.exchange.domain.exceptions;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ApiException {

    private String code;
    private String description;
    private List<ErrorDetail> errorDetails;

}

