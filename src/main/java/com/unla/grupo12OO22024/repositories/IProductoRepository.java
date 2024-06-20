package com.unla.grupo12OO22024.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo12OO22024.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    //@Query("SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    public abstract Producto findByDescripcion ( String descripcion );

    @Query("from Producto p where p.stock < p.stock_minimo")
    public abstract List<Producto> findAllLowStock();

    //Maximiliano
    //query trae todos los productos de la DB cu stock este por encima de las
    // 20 unidaddes de su stock minimo
    @Query("from Producto p where p.stock > p.stock_minimo and p.stock < p.stock_minimo+20")
    public abstract List<Producto> findAllCloseToLowStock();
}
