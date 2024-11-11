package com.tecnica.exchange.domain.repository;

import com.tecnica.exchange.domain.model.ExchangeApiResponse;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ExchangeRecordRepository  extends ReactiveCrudRepository<ExchangeApiResponse, Long> {
}
