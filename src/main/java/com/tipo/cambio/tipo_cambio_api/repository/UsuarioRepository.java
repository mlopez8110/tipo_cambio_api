package com.tipo.cambio.tipo_cambio_api.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.tipo.cambio.tipo_cambio_api.model.Usuario;

import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, Long> {
    Mono<Usuario> findByUsername(String username);
}
