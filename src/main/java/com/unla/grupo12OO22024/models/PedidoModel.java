package com.unla.grupo12OO22024.models;

import java.time.LocalDate;

import com.unla.grupo12OO22024.entities.Producto;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PedidoModel {

	@Id
	@Setter(AccessLevel.PROTECTED)
	private int id_pedido;

	private Producto producto;

	private int cantidad;

	private LocalDate fecha;

	private String proveedor;

	public PedidoModel(int idPedido, Producto producto, int cantidad, LocalDate fecha, String proveedor) {
		this.id_pedido = idPedido;
		this.producto = producto;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.proveedor = proveedor;
	}

}