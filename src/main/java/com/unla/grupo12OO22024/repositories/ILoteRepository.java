package com.unla.grupo12OO22024.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo12OO22024.entities.Lote;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Long>{
    
}
