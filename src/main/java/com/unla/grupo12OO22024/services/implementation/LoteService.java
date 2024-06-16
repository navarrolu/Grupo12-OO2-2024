package com.unla.grupo12OO22024.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Lote;
import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.LoteModel;
import com.unla.grupo12OO22024.repositories.ILoteRepository;
import com.unla.grupo12OO22024.repositories.IProductoRepository;
import com.unla.grupo12OO22024.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService{

    @Autowired
    @Qualifier("loteRepository")
    private ILoteRepository loteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public LoteModel insertOrUpdate(LoteModel loteModel) {
        //convertir a entidad
        Lote lote = modelMapper.map(loteModel, Lote.class);

        //Obtener el producto
        Producto producto = lote.getProducto();
        int cantStockNuevo = lote.getCantidad();

        //calcular nuevo stock
        producto.setStock(producto.getStock() + cantStockNuevo);
        productoRepository.save(producto);

        //guardar lote
        lote = loteRepository.save(lote);

        //pasar a modelo 
        return modelMapper.map(lote, LoteModel.class);
    }

    private Lote modelToEntity(LoteModel loteModel) {
        Lote lote = new Lote();
        lote.setCantidad(loteModel.getCantidad());
        lote.setFechaRecepcion(loteModel.getFechaRecepcion());
        lote.setProveedor(loteModel.getProveedor());
        lote.setPrecio(loteModel.getPrecio());
        Producto producto = productoRepository.findById(loteModel.getProducto().getId_producto()).orElse(null);
        lote.setProducto(producto);
        return lote;
    }


}
