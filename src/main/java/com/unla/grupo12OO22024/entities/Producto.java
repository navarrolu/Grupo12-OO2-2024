package com.unla.grupo12OO22024.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "producto")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private int id_producto;

    @Column(name = "descripcion", nullable = false)
    private  String descripcion;
    @Column(name = "precio_total", nullable = false)
    private float precio_total;
    @Column(name = "stock", nullable = false)
    private int stock;
    @Column(name = "stock_minimo", nullable = false)
    private int stock_minimo;

}
