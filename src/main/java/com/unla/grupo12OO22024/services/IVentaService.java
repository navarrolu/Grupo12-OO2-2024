package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.entities.Venta;
import com.unla.grupo12OO22024.models.VentaModel;

public interface IVentaService {

	public VentaModel insertOrUpdate(VentaModel ventaModel);

	public List<Venta> getAll();
}
