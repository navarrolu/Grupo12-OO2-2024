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

    @Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}
	


    @Transactional
    public LoteModel insertOrUpdate(LoteModel loteModel) {
        
        //convertir a entidad
        Lote lote = modelMapper.map(loteModel, Lote.class);

        Optional<Pedido> pedido = pedidoRepository.findById(loteModel.getPedido().getId_pedido());
        if(pedido != null){
            Pedido pedidoEntitie =  pedido.get();
            lote.setPedido(pedidoEntitie);
            if(loteModel.getPedido().getProducto() !=null){
                Optional<Producto> optionalProducto = productoRepository.findById(pedidoEntitie.getProducto().getId_producto());
                //Optional<Producto> optionalProducto = productoRepository.findById(loteModel.getPedido().getProducto().getId_producto());
                Producto producto = optionalProducto.get();
                producto.setStock(producto.getStock() + pedidoEntitie.getProducto().getStock());
                productoRepository.save(producto);

                 //guardar lote
                lote = loteRepository.save(lote);
                //pasar a modelo 
            }
        }
        return modelMapper.map(lote, LoteModel.class);
   
       

        
    }



}
