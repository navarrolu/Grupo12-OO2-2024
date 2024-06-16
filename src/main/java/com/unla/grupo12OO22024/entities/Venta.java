package com.unla.grupo12OO22024.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "venta")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta", nullable = false)
	private long idVenta;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;

	@Column(name = "id_usuario", nullable = false)
	private int usuario;
	
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	
	@Column (name = "total", nullable = false)
	private float total;
	
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	
	// Antes de que una nueva instancia de la entidad se inserte en la base de datos, 
	// JPA llamará automáticamente a cualquier método anotado con @PrePersist
	@PrePersist
	public void prePersist() {
		this.fecha = LocalDate.now(); 
	}	
}
