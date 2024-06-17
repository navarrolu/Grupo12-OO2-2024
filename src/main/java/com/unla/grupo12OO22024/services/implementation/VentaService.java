package com.unla.grupo12OO22024.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.entities.Venta;
import com.unla.grupo12OO22024.models.VentaModel;
import com.unla.grupo12OO22024.repositories.IProductoRepository;
import com.unla.grupo12OO22024.repositories.IVentaRepository;
import com.unla.grupo12OO22024.services.IVentaService;

import jakarta.transaction.Transactional;

@Service("ventaService")
public class VentaService implements IVentaService {

	private IVentaRepository ventaRepository;
	private IProductoRepository productoRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public VentaService(IVentaRepository ventaRepository, IProductoRepository productoRepository) {
		this.ventaRepository = ventaRepository;
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Venta> getAll() {
		return ventaRepository.findAll();
	}
	
	@Transactional
	public List<VentaModel> findByUsuario(int usuario) {
		List<Venta> ventas = ventaRepository.findByUsuario(usuario);
		return ventas.stream().map(venta -> modelMapper.map(venta, VentaModel.class)).collect(Collectors.toList());
	}

	@Transactional
	public VentaModel insertOrUpdate(VentaModel ventaModel) {
		// Convertir VentaModel a entidad Venta
		Venta venta = modelMapper.map(ventaModel, Venta.class);

		// Obtener el producto asociado
		Producto producto = venta.getProducto();
		int cantidadVenta = venta.getCantidad();

		// Verificar si hay suficiente stock
		if (producto.getStock() < cantidadVenta) {
			throw new IllegalArgumentException("No hay suficiente stock disponible para el producto.");
		}

		// Calcular el total de la venta
		float totalVenta = cantidadVenta * producto.getPrecio_total();
		venta.setTotal(totalVenta);

		// Actualizar el stock del producto
		producto.setStock(producto.getStock() - cantidadVenta);
		productoRepository.save(producto);

		// Guardar la venta
		venta = ventaRepository.save(venta);

		// Convertir la entidad Venta a VentaModel y devolver
		return modelMapper.map(venta, VentaModel.class);
	}


}
