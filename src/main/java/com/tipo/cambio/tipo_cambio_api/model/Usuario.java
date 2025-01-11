package com.tipo.cambio.tipo_cambio_api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("usuario")
public class Usuario {
    @Id
    private Long id;
    private String username;
    private String password;
    private String role;
}