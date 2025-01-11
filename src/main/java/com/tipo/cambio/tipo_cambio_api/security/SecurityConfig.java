package com.tipo.cambio.tipo_cambio_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.RequiredArgsConstructor;

import org.springframework.security.config.web.server.SecurityWebFiltersOrder;


// @Configuration
// @EnableWebFluxSecurity
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthenticationFilter;
//     private final JwtAuthenticationManager jwtAuthenticationManager;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationManager jwtAuthenticationManager) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//         this.jwtAuthenticationManager = jwtAuthenticationManager;
//     }

//     @Bean
//     public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//         http.csrf().disable()
//             .authorizeExchange()
//                 .pathMatchers("/api/auth/registrar", "/api/auth/login", "/h2-console/**").permitAll()
//                 .anyExchange().authenticated()
//             .and()
//             .headers().frameOptions().disable()
//             .and()
//             .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
//             .httpBasic().disable();

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder(); 
//     }
// }

@Configuration
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/auth/registrar", "/api/auth/login", "/h2-console/**").permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }    
}




