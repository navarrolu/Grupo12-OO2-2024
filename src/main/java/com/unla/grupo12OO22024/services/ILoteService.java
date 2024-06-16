package com.unla.grupo12OO22024.services;

import com.unla.grupo12OO22024.models.LoteModel;

public interface ILoteService {

    //public List<Lote> getAll();
    public LoteModel insertOrUpdate(LoteModel loteModel);
    //public LoteModel saveLote(LoteModel loteModel);
}
