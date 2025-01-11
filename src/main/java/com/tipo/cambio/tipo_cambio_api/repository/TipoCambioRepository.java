package com.tipo.cambio.tipo_cambio_api.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.tipo.cambio.tipo_cambio_api.model.TipoCambio;

import reactor.core.publisher.Mono;

public interface TipoCambioRepository extends ReactiveCrudRepository<TipoCambio, Long> {
    Mono<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
