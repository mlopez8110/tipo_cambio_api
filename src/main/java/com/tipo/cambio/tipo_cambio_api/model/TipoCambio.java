package com.tipo.cambio.tipo_cambio_api.model;

// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Data
// @Table(name="tipo_cambio")
// public class TipoCambio {
	
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     private String monedaOrigen;
//     private String monedaDestino;
//     private Double tipoCambio;

// }

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tipo_cambio")
public class TipoCambio {
    @Id
    private Long id;
    
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
}
