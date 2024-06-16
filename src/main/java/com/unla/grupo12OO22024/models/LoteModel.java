package com.unla.grupo12OO22024.models;

import com.unla.grupo12OO22024.entities.Producto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    private long id_lote;

    private int cantidad;

    private java.sql.Timestamp fechaRecepcion;

    @NotBlank
    private String proveedor;

    @Min(0)
    private float precio;

    private Producto producto;

    private long pedido_nro;

    public LoteModel(long id_lote, int cantidad, String proveedor, float precio, Producto producto, long pedido_nro) {
        this.id_lote = id_lote;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.precio = precio;
        this.producto = producto;
        this.pedido_nro = pedido_nro;
    }
}
