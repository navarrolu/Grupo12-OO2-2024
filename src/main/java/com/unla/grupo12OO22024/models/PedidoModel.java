package com.unla.grupo12OO22024.models;

import java.time.LocalDate;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PedidoModel {

	@Id
	@Setter(AccessLevel.PROTECTED)
	private int idPedido;
	private long producto;
	private int cantidad;
	private LocalDate fecha;
	private String proveedor;

	public PedidoModel(int id,long producto, int cantidad, LocalDate fecha, String proveedor) {
		this.setIdPedido(id);
		this.producto = producto;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.proveedor = proveedor;
	}

}
