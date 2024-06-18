package com.unla.grupo12OO22024.models;

import java.time.LocalDate;

import com.unla.grupo12OO22024.entities.Producto;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // Genera automaticamente los metodos getter de la clase
@Setter // Genera automaticamente los metodos setter de la clase
@ToString // Genera automaticamente el metodo toString de la clase
@NoArgsConstructor // Genera automáticamente un constructor sin argumentos
public class PedidoModel {

	@Id
	@Setter(AccessLevel.PROTECTED)
	private int id_pedido;

	private Producto producto;

	private int cantidad;

	private float total;

	private LocalDate fecha;

	private String proveedor;

	// Constructor con argumentos para inicializar los campos del modelo
	public PedidoModel(int idPedido, Producto producto, int cantidad, float total, LocalDate fecha, String proveedor) {
		this.id_pedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.total = total;
		this.fecha = fecha;
		this.proveedor = proveedor;
	}

}