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
public class VentaModel {

	@Id
	@Setter(AccessLevel.PROTECTED)
	private long idVenta;
	
	private Producto producto;
	
	private int usuario;
	
	private int cantidad;
	
	private LocalDate fecha;

	public VentaModel(long idVenta, Producto producto, int usuario, int cantidad, LocalDate fecha) {
		this.idVenta= idVenta;
		this.producto = producto;
		this.usuario = usuario;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

}
