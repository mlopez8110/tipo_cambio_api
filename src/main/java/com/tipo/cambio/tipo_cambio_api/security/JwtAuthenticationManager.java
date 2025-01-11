package com.tipo.cambio.tipo_cambio_api.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import reactor.core.publisher.Mono;


@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String username = (String) authentication.getPrincipal();

        if (authentication.getCredentials() == null) {
            return Mono.empty();
        }

        if (jwtUtil.extraerUsername(authentication.getCredentials().toString()).equals(username)) {
            return Mono.just(new UsernamePasswordAuthenticationToken(username, null, 
                java.util.Collections.singletonList(new SimpleGrantedAuthority("USER"))));
        }
        return Mono.empty();
    }
}


