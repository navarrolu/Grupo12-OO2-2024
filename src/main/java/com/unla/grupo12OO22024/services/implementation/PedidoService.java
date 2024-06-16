package com.unla.grupo12OO22024.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.models.PedidoModel;
import com.unla.grupo12OO22024.repositories.IPedidoRepository;
import com.unla.grupo12OO22024.services.IPedidoService;


@Service("pedidoService")
public class PedidoService implements IPedidoService {

	private IPedidoRepository pedidoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public PedidoService(IPedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	@Override
	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}
	
	@Override
	public PedidoModel insertOrUpdate(PedidoModel pedidoModel) {	
		Pedido pedido = pedidoRepository.save(modelMapper.map(pedidoModel, Pedido.class));
		return modelMapper.map(pedido, PedidoModel.class);
	}
	
}
