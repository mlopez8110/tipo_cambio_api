package com.tipo.cambio.tipo_cambio_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String generarToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact(); 
    }


    public String extraerUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("El token ha expirado", e);
        } catch (JwtException e) {
            throw new RuntimeException("Error al parsear el token JWT", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer el nombre de usuario del token", e);
        }
    }    

    public String extraerRol(String token) {
        try {
            Claims claims = Jwts.parser()
                                .setSigningKey(secretKey)
                                .parseClaimsJws(token)
                                .getBody();
            
            return claims.get("role", String.class);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("El token ha expirado", e);
        } catch (JwtException e) {
            throw new RuntimeException("Error al parsear el token JWT", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer el rol del token", e);
        }
    }

}
