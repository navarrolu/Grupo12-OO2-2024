package com.unla.grupo12OO22024.models;

import java.time.LocalDate;

import com.unla.grupo12OO22024.entities.Pedido;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoteModel {

    @Id
    @Setter(AccessLevel.PROTECTED)
    private Long id_lote;

    private LocalDate fecha_recepcion;

    private Pedido pedido;

    public LoteModel(Long id_lote, Pedido pedido, LocalDate fecha_recepcion) {
        this.id_lote = id_lote;
        this.pedido = pedido;
        this.fecha_recepcion = fecha_recepcion;
    }
}
