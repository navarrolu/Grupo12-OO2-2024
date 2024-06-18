package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.entities.Venta;
import com.unla.grupo12OO22024.models.VentaModel;

public interface IVentaService {

	// Rocio
	// Método para obtener las compras realizados por todos los usuarios	
	public List<Venta> getAll();
	
	// Rocio
	// Método para obtener las compras realizados por el usuario logeado
	public List<Venta> obtenerVentasPorUsuario();

	// Rocio
	// Método para insertar una nueva venta y devolver la VentaModel creada
	public VentaModel insertVenta(VentaModel ventaModel);

}
