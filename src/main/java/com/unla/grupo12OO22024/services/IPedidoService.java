package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.models.PedidoModel;

public interface IPedidoService {

	public List<Pedido> getAll();

	public PedidoModel insertOrUpdate(PedidoModel pedidoModel);

}
