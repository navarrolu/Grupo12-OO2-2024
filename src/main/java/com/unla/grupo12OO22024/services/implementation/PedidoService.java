package com.unla.grupo12OO22024.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.PedidoModel;
import com.unla.grupo12OO22024.repositories.IPedidoRepository;
import com.unla.grupo12OO22024.repositories.IProductoRepository;
import com.unla.grupo12OO22024.services.IPedidoService;

//Indica que esta clase es un componente de "pedidoService"
@Service("pedidoService")
public class PedidoService implements IPedidoService {

	// Rocio
	// Repositorio de pedidos
	private IPedidoRepository pedidoRepository;

	// Rocio
	// Repositorio de pedidos
	private IProductoRepository productoRepository;

	// Rocio
	// Instancia de ModelMapper para mapear entre modelos y entidades
	private ModelMapper modelMapper = new ModelMapper();

	// Rocio
	// Constructor que recibe el repositorio de pedidos como dependencia
	public PedidoService(IPedidoRepository pedidoRepository, IProductoRepository productoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.productoRepository = productoRepository;
	}

	// Rocio
	/*
	 * Método para obtener todos los pedidos
	 * 
	 * Utilizo "findAll()" del repositorio para obtenerlos
	 */
	@Override
	public List<Pedido> getAll() {
		return pedidoRepository.findAll();
	}

	// Rocio
	/*
	 * Método para insertar un nuevo pedido y devolver el PedidoModel creado
	 * 
	 * Se busca el producto por su ID y se calcula el total y se setea, para despues
	 * mapear PedidoModel que recibe la entidad Pedido y lo guarda en el repositorio
	 * y retorna PedidoModel que fue mapeado con el pedido guardado
	 */
	@Override
	public PedidoModel insertPedido(PedidoModel pedidoModel) {
		Producto producto = productoRepository.findById(pedidoModel.getProducto().getId_producto())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		float total = pedidoModel.getCantidad() * producto.getPrecio_base();
		pedidoModel.setTotal(total);
		Pedido pedido = pedidoRepository.save(modelMapper.map(pedidoModel, Pedido.class));
		return modelMapper.map(pedido, PedidoModel.class);
	}

}
