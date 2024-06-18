package com.unla.grupo12OO22024.models;

import java.time.LocalDate;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.entities.User;

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
public class VentaModel {

	@Id
	@Setter(AccessLevel.PROTECTED)
	private int id_venta;
	
	private Producto producto;
	
	private User usuario;
	
	private int cantidad;
	
	private float total;
	
	private LocalDate fecha;

	// Constructor con argumentos para inicializar los campos del modelo
	public VentaModel(int idVenta, Producto producto, User usuario, int cantidad, LocalDate fecha) {
		this.id_venta= idVenta;
		this.producto = producto;
		this.usuario = usuario;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

}
