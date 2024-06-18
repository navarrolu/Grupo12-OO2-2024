package com.unla.grupo12OO22024.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.entities.User;
import com.unla.grupo12OO22024.entities.Venta;
import com.unla.grupo12OO22024.models.VentaModel;
import com.unla.grupo12OO22024.repositories.IProductoRepository;
import com.unla.grupo12OO22024.repositories.IUserRepository;
import com.unla.grupo12OO22024.repositories.IVentaRepository;
import com.unla.grupo12OO22024.services.IVentaService;

import jakarta.transaction.Transactional;

//Indica que esta clase es un componente de "ventaService"
@Service("ventaService")
public class VentaService implements IVentaService {

	// Rocio
	// Repositorios para gestionar ventas, productos y usuarios
	private IVentaRepository ventaRepository;
	private IProductoRepository productoRepository;
	private IUserRepository userRepository;

	// Instancia de ModelMapper para mapear entre modelos y entidades
	private ModelMapper modelMapper = new ModelMapper();

	// Rocio
	// Constructor que recibe los repositorios necesarios como dependencias
	public VentaService(IVentaRepository ventaRepository, IProductoRepository productoRepository,
			IUserRepository userRepository) {
		this.ventaRepository = ventaRepository;
		this.productoRepository = productoRepository;
		this.userRepository = userRepository;
	}

	// Rocio
	/*
	 * Método para obtener todas las ventas
	 * 
	 * Utilizo "findAll()" del repositorio para obtenerlas
	 */
	public List<Venta> getAll() {
		return ventaRepository.findAll();
	}

	// Rocio
	/*
	 * Método para obtener las ventas del usuario autenticado
	 * 
	 * Se obtiene el usuario autenticado, y se buscan y retornan las ventas
	 * realizadas por ese usuario con el método findByUsuarioId() del repositorio
	 */
	public List<Venta> obtenerVentasPorUsuario() {
		User usuario = getAuthenticatedUser();
		return ventaRepository.findByUsuarioId(usuario.getId());
	}

	// Rocio
	/*
	 * Método para insertar una nueva venta
	 * 
	 * Se mapea la entidad Venta en VentaModel, se obtiene el producto y la cantidad
	 * a vender, y se verifica si hay suficiente stock, si lo hay calcula el total
	 * de la venta,lo establece en la entidad y actualiza el stock del producto.
	 * 
	 * Se obtiene el usuario autenticado y se lo establece en la venta, esta se
	 * guarda y se retorna. *
	 */
	@Transactional
	public VentaModel insertVenta(VentaModel ventaModel) {
		Venta venta = modelMapper.map(ventaModel, Venta.class);

		Producto producto = venta.getProducto();
		int cantidadVenta = venta.getCantidad();

		if (producto.getStock() < cantidadVenta) {
			throw new IllegalArgumentException("No hay suficiente stock disponible para el producto.");
		}

		float totalVenta = cantidadVenta * producto.getPrecio_total();
		venta.setTotal(totalVenta);

		producto.setStock(producto.getStock() - cantidadVenta);
		productoRepository.save(producto);

		User usuario = getAuthenticatedUser();
		venta.setUsuario(usuario);

		venta = ventaRepository.save(venta);

		return modelMapper.map(venta, VentaModel.class);
	}

	// Rocio
	/*
	 * Método privado para obtener el usuario autenticado
	 * 
	 * Se obtiene la autenticacion del usuario actual, se verifica que sea valida y
	 * si se pudo obtener el nombre del usuario, se busca
	 */
	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = null;

		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			username = userDetails.getUsername();
		} else if (authentication != null) {
			username = authentication.getPrincipal().toString();
		}

		if (username != null) {
			return (userRepository.findByUsername(username))
					.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
		} else {
			throw new IllegalArgumentException("No se pudo obtener el usuario autenticado.");
		}
	}
}
