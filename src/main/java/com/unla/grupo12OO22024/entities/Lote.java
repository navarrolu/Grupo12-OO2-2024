package com.unla.grupo12OO22024.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "lote")
@ToString
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private Long id_lote;

    @Column(name = "cantidad", nullable = false)
    @Min(0)
    private int cantidad;

    @Column(name = "fecha_recepcion")
    private java.sql.Timestamp fechaRecepcion;


    @Column(name = "proveedor", nullable = false)
    private String proveedor;

    @Min(0)
    @Column(name = "precio", nullable = false)
    private float precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    
    @Column(name = "pedido_nro", nullable = false)
    private Long pedido_nro;


}
