package com.unla.grupo12OO22024.models;

import java.time.LocalDate;

import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.entities.Producto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoteModel {

    @Id
    @Setter(AccessLevel.PROTECTED)
    private Long id_lote;

    private int cantidad;

    private LocalDate fecha_recepcion;

    @NotBlank
    private String proveedor;

    @Min(0)
    private float precio;

    @NotNull
    private Producto producto;

    private Pedido pedido;

    public LoteModel(Long id_lote, int cantidad, String proveedor, float precio, Producto producto, Pedido pedido, LocalDate fecha_recepcion) {
        this.id_lote = id_lote;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.precio = precio;
        this.producto = producto;
        this.pedido = pedido;
        this.fecha_recepcion = fecha_recepcion;
    }
}
