package com.unla.grupo12OO22024.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Lote;
import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.LoteModel;
import com.unla.grupo12OO22024.repositories.ILoteRepository;
import com.unla.grupo12OO22024.repositories.IProductoRepository;
import com.unla.grupo12OO22024.services.ILoteService;

import jakarta.transaction.Transactional;

@Service("loteService")
public class LoteService implements ILoteService{

    @Autowired
    @Qualifier("loteRepository")
    private ILoteRepository loteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional
    public Lote saveLote(LoteModel loteModel) {
        Lote lote = new Lote();
        lote.setCantidad(loteModel.getCantidad());
        lote.setFechaRecepcion(loteModel.getFechaRecepcion());
        lote.setProveedor(loteModel.getProveedor());
        lote.setPrecio(loteModel.getPrecio());
        //lote.setProducto(loteModel.getProducto());
        Producto producto = loteModel.getProducto();
        if (producto != null) {
            return loteRepository.save(lote);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    //private ModelMapper modelMapper = new ModelMapper();

    /*@Override
    public List<Lote> getAll() {
        return loteRepository.findAll();
    }*/

    //TODO implementar demas metodos

    /*public void saveLote(LoteModel loteModel){
        Lote lote = loteRepository.save ( modelMapper.map(loteModel, 
        Lote.class));
       modelMapper.map(lote, LoteModel.class);
    };*/



}
