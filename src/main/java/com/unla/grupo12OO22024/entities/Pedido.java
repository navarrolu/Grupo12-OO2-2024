package com.unla.grupo12OO22024.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;;

@Entity // se mapea a una tabla en la base de datos
@Getter // Genera automaticamente los metodos getter de la clase
@Setter // Genera automaticamente los metodos setter de la clase
@ToString // Genera automaticamente el metodo toString de la clase
@NoArgsConstructor // Genera automáticamente un constructor sin argumentos
@Table(name = "pedido")
public class Pedido {

	// Rocio
	/*
	 * Mapeo con tablas de la base de datos
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.PROTECTED)
	private Long id_pedido;

	@OneToOne
    @JoinColumn(name = "id_producto", nullable = false)
	private Producto producto;

	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	@Column(name = "total", nullable = false)
	private float total;

	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@Column(name = "proveedor", nullable = false)
	private String proveedor;

}