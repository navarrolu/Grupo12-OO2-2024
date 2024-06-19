package com.unla.grupo12OO22024.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo12OO22024.entities.Pedido;

//Indica que esta interfaz es un componente de "pedidoRepository"
@Repository("pedidoRepository")
public interface IPedidoRepository extends JpaRepository<Pedido, Long>{

}
