package com.unla.grupo12OO22024.services;

import com.unla.grupo12OO22024.entities.Lote;
import com.unla.grupo12OO22024.models.LoteModel;

public interface ILoteService {

    //public List<Lote> getAll();
    Lote saveLote(LoteModel loteModel);
    //public LoteModel saveLote(LoteModel loteModel);
}
