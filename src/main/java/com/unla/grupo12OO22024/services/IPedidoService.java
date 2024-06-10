package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.entities.Pedido;

public interface IPedidoService {

	public List<Pedido> getAll();

	public Pedido insertOrUpdate(Pedido pedido);
}
