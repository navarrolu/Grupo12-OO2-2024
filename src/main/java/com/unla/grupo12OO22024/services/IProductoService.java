package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.ProductoModel;

public interface IProductoService {



    public List<Producto> getAll();


    //Maximiliano
    //alta y modificacion de productos
    public ProductoModel insertOrUpdate( ProductoModel productoModel );

    //Maximiliano
    //elimina un producto por iD
    public  boolean remove ( long id );


    //Maximiliano
    //trae todos los productos con stock bajo
    public List<Producto> findAllLowStock();

    //Maximiliano
    //trae todos los productos con stock cercanos a stock minimo

    public List<Producto> findAllCloseToLowStock();

}
