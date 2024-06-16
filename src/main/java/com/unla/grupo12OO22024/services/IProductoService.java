package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.ProductoModel;

public interface IProductoService {

    public List<Producto> getAll();
    public Producto traerPorNombre(String nombre);
    public ProductoModel insertOrUpdate( ProductoModel productoModel );
    public ProductoModel convertToModel(Producto producto);
    public  boolean remove ( long id );
    public ProductoModel getById(long productoNombre);

}
