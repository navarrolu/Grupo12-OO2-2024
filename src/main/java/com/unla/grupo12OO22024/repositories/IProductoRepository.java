package com.unla.grupo12OO22024.repositories;

import com.unla.grupo12OO22024.entities.Producto;
import com.unla.grupo12OO22024.models.ProductoModel;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    public abstract Producto findByDescripcion ( String descripcion );



}
