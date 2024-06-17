package com.unla.grupo12OO22024.entities;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "lote")
@ToString
@NoArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote", nullable = false)
    private Long id_lote;

    @Column(name = "cantidad", nullable = false)
    @Min(0)
    private int cantidad;

    @Column(name = "fecha_recepcion")
    private LocalDate fecha_recepcion;


    @Column(name = "proveedor", nullable = false)
    private String proveedor;

    @Min(0)
    @Column(name = "precio", nullable = false)
    private float precio;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = true)
    private Pedido pedido;


    // Antes de que una nueva instancia de la entidad se inserte en la base de datos, 
	// JPA llamará automáticamente a cualquier método anotado con @PrePersist
    // creditos a Ro!! <3
	@PrePersist
	public void prePersist() {
		this.fecha_recepcion = LocalDate.now(); 
	}
}
