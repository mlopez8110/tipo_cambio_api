package com.tipo.cambio.tipo_cambio_api.service;

import org.springframework.stereotype.Service;

import com.tipo.cambio.tipo_cambio_api.model.TipoCambio;
import com.tipo.cambio.tipo_cambio_api.repository.TipoCambioRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TipoCambioService {

    private final TipoCambioRepository repository;

    public Mono<Double> convertir(String monedaOrigen, String monedaDestino, Double monto) {
        return repository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino)
                .map(tipoCambio -> monto * tipoCambio.getTipoCambio());
    }

    public Mono<TipoCambio> registrar(TipoCambio tipoCambio) {
        return repository.save(tipoCambio);
    }
}
