package com.tipo.cambio.tipo_cambio_api.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;

import reactor.core.publisher.Mono;


// @Component
// public class JwtAuthenticationFilter extends AuthenticationWebFilter {

//     private final JwtUtil jwtUtil;

//     public JwtAuthenticationFilter(JwtAuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//         super(authenticationManager);
//         this.jwtUtil = jwtUtil;
//         setServerAuthenticationConverter(exchange -> {
//             String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//             if (token != null && token.startsWith("Bearer ")) {
//                 String username = jwtUtil.extraerUsername(token.substring(7)); 
//                 String role = jwtUtil.extraerRol(token.substring(7));
//                 System.err.println("nombre de usuario: " + username);
//                 System.err.println("Rol: " + role);

//             List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
//                 return Mono.just(new UsernamePasswordAuthenticationToken(username, null, authorities)); 
//             }
//             return Mono.empty();
//         });
//     }

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, null);

                SecurityContext context = new SecurityContextImpl(auth);
                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
            } else {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }

}

