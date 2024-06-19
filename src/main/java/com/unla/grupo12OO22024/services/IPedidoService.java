package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.models.PedidoModel;

public interface IPedidoService {

	// Rocio
	// Método para obtener todos los pedidos
	public List<Pedido> getAll();

	// Rocio
	// Método para insertar un nuevo pedido y devolver el PedidoModel creado
	public PedidoModel insertPedido(PedidoModel pedidoModel);

}
