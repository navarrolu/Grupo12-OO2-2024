package com.unla.grupo12OO22024.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.repositories.ILoteRepository;
import com.unla.grupo12OO22024.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService{

    @Autowired
    @Qualifier("loteRepository")
    private ILoteRepository loteRepository;

    private ModelMapper modelMapper = new ModelMapper();

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
