package com.unla.grupo12OO22024.models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductoModel {
        @Id
        @Setter(AccessLevel.PROTECTED)
        private long id_producto;
        @NotBlank
        private  String descripcion;
        @Min(0)
        private float precio_total;
        @Min(0)
        private int stock;
        @Min(0)
        private int stock_minimo;

    public ProductoModel(long id_producto, String descripcion, float precio_total, int stock, int stock_minimo) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio_total = precio_total;
        this.stock = stock;
        this.stock_minimo = stock_minimo;
    }
}
