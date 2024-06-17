package com.unla.grupo12OO22024.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo12OO22024.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    //@Query("SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    public abstract Producto findByDescripcion ( String descripcion );
    
}
