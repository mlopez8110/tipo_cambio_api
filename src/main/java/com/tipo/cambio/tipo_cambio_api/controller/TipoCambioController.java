package com.tipo.cambio.tipo_cambio_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipo.cambio.tipo_cambio_api.model.TipoCambio;
import com.tipo.cambio.tipo_cambio_api.service.TipoCambioService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tipo-cambio")
@RequiredArgsConstructor
public class TipoCambioController {

    private final TipoCambioService service;

    @PostMapping("/convertir")
    public Mono<Double> convertir(@RequestParam String origen, @RequestParam String destino, @RequestParam Double monto) {
        return service.convertir(origen, destino, monto);
    }

    @PostMapping("/registrar")
    public Mono<TipoCambio> registrar(@RequestBody TipoCambio tipoCambio) {
        return service.registrar(tipoCambio);
    }
}
