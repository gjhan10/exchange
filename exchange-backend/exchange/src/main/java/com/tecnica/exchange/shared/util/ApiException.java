package com.tecnica.exchange.shared.util;

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

