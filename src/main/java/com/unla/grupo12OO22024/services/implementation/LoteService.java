package com.unla.grupo12OO22024.services.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Lote;
import com.unla.grupo12OO22024.entities.Pedido;
import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.LoteModel;
import com.unla.grupo12OO22024.repositories.ILoteRepository;
import com.unla.grupo12OO22024.repositories.IPedidoRepository;
import com.unla.grupo12OO22024.repositories.IProductoRepository;
import com.unla.grupo12OO22024.services.ILoteService;

import jakarta.transaction.Transactional;

@Service("loteService")
public class LoteService implements ILoteService{


    @Qualifier("loteRepository")
    private ILoteRepository loteRepository;
    
    private ModelMapper modelMapper = new ModelMapper();

    private IProductoRepository productoRepository;

    private IPedidoRepository pedidoRepository;


    public LoteService(ILoteRepository loteRepository, IProductoRepository productoRepository, IPedidoRepository pedidoRepository) {
		this.loteRepository = loteRepository;
        this.productoRepository = productoRepository;
        this.pedidoRepository = pedidoRepository;
	}

    //llamar a todos los lotes en una lista para luego ser usada en la vista del html
    @Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}
	

    //guardar el lote del form
    @Transactional
    public LoteModel insertOrUpdate(LoteModel loteModel) {
        
        //convertir el modelo de lote a entidad
        Lote lote = modelMapper.map(loteModel, Lote.class);

        //consigo el id del pedido que contiene el lote
        long id_pedido = loteModel.getPedido().getId_pedido();
        
        //busco el registror de la base de datos que contiene ese id
        Optional<Pedido> pedido = pedidoRepository.findById(id_pedido);

        //pregunto si lo logro encontrar
        if(pedido.isPresent()){
            //paso el pedido optional a una entidad pedido
            Pedido pedidoEntitie =  pedido.get();

            //seteo en mi entidad lote la entidad pedido
            lote.setPedido(pedidoEntitie);

            // Crear el nuevo lote
            loteRepository.save(lote);
 
            //en una entidad producto alojo el producto de la entidad pedido
            Producto producto = pedidoEntitie.getProducto();
            
            //actualizar el stock del producto
            producto.setStock(producto.getStock() + pedidoEntitie.getCantidad());

            //actualizo mi producto con su nuevo stock
            productoRepository.save(producto);
        }
        return modelMapper.map(lote, LoteModel.class);
        
    }

}
