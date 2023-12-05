package com.company.springframework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "tbl_producto")
@Entity
@Getter
@Setter


public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    private long stock;

    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

}
