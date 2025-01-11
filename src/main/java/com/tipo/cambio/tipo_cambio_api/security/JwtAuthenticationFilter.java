package com.tipo.cambio.tipo_cambio_api.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;

import java.util.*;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;


@Component
public class JwtAuthenticationFilter extends AuthenticationWebFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtAuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        setServerAuthenticationConverter(exchange -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (token != null && token.startsWith("Bearer ")) {
                String username = jwtUtil.extraerUsername(token.substring(7)); 
                String role = jwtUtil.extraerRol(token.substring(7));
                System.err.println("nombre de usuario: " + username);
                System.err.println("Rol: " + role);

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
                return Mono.just(new UsernamePasswordAuthenticationToken(username, null, authorities)); 
            }
            return Mono.empty();
        });
    }
}

