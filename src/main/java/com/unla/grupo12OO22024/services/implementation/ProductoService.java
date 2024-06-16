package com.unla.grupo12OO22024.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.ProductoModel;
import com.unla.grupo12OO22024.repositories.IProductoRepository;
import com.unla.grupo12OO22024.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {

    @Autowired
    @Qualifier("productoRepository")
    private IProductoRepository productoRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoModel insertOrUpdate(ProductoModel productoModel) {

        Producto producto = productoRepository.save( modelMapper.map(productoModel, Producto.class));
        return modelMapper.map(producto, ProductoModel.class);
    }

    public  ProductoModel getById ( long id ){
        Producto producto = productoRepository.getReferenceById(id);
        return  modelMapper.map(producto, ProductoModel.class);
    }

    @Override
    public boolean remove(long id) {
        try {
            productoRepository.deleteById(id);
            return  true;
        } catch ( Exception e){
            return false;
        }
    }

    public ProductoModel traerPorNombre (String nombre){
        Producto producto = productoRepository.findByDescripcion(nombre);
        ProductoModel productoModel = this.convertToModel(producto);
        return productoModel;
    }

    private ProductoModel convertToModel(Producto producto) {
       ProductoModel productoModel = modelMapper.map(producto, ProductoModel.class);
        return productoModel;
    }



}
