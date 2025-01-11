package com.tipo.cambio.tipo_cambio_api.controller;

import com.tipo.cambio.tipo_cambio_api.model.Usuario;
import com.tipo.cambio.tipo_cambio_api.security.JwtUtil;
import com.tipo.cambio.tipo_cambio_api.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth") 
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;


    @PostMapping("/registrar")
    public Mono<ResponseEntity<String>> register(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario)
                .map(savedUser -> ResponseEntity.ok("Usuario registrado correctamente"))
                .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body("Error al registrar el usuario")));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody Usuario usuario) {
        return usuarioService.obtenerUsuarioPorUsername(usuario.getUsername())
                .flatMap(u -> {
                    if (usuarioService.verificarPassword(usuario.getPassword(), u.getPassword())) {
                        String token = jwtUtil.generarToken(u.getUsername(),u.getRole());
                        return Mono.just(ResponseEntity.ok(token));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas"));
                    }
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado")));
    }

}
