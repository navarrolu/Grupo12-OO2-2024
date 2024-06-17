package com.unla.grupo12OO22024.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Long id_producto;

    @Column(name = "descripcion", nullable = false)
    private  String descripcion;
    @Column(name = "precio_total", nullable = false)
    private float precio_total;
    @Column(name = "stock", nullable = false)
    private int stock;
    @Column(name = "stock_minimo", nullable = false)
    private int stock_minimo;

}
