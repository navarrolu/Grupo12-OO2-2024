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


	//Maximiliano
	//alta y modificacion de productos
	@Override
	public ProductoModel insertOrUpdate(ProductoModel productoModel) {
		// Setteo de precio total
		productoModel.setPrecio_total(productoModel.getPrecio_base() * 1.25f);

		Producto producto = productoRepository.save(modelMapper.map(productoModel, Producto.class)); // clase productoModel  a Producto
		return modelMapper.map(producto, ProductoModel.class); // devulve un ProductoModel
	}


	//Maximiliano
	//obtiene un producto por iD
	public ProductoModel getById(long id) {
		Producto producto = productoRepository.getReferenceById(id);
		return modelMapper.map(producto, ProductoModel.class);
	}


	//Maximiliano
	//elimina un producto por iD
	@Override
	public boolean remove(long id) {
		try {
			productoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//Maximiliano
	//trae todos los productos con stock bajo
    @Override
    public List<Producto> findAllLowStock() {
        return productoRepository.findAllLowStock();
    }


	//Maximiliano
	//trae todos los productos con stock cercanos a stock minimo
    @Override
    public List<Producto> findAllCloseToLowStock() {
        return productoRepository.findAllCloseToLowStock();
    }

}
