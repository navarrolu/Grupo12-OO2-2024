package com.unla.grupo12OO22024.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo12OO22024.entities.Venta;

// Indica que esta interfaz es un componente de "ventaRepository"
@Repository("ventaRepository")
public interface IVentaRepository extends JpaRepository<Venta, Serializable> {

	// Método para buscar ventas por ID de usuario
	List<Venta> findByUsuarioId(Long idUsuario);

}
