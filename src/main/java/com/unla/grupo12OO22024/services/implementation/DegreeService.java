package com.unla.grupo12OO22024.services.implementation;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo12OO22024.entities.Degree;
import com.unla.grupo12OO22024.models.DegreeModel;
import com.unla.grupo12OO22024.repositories.IDegreeRepository;
import com.unla.grupo12OO22024.services.IDegreeService;

@Service("degreeService")
public class DegreeService implements IDegreeService{
    @Autowired
    @Qualifier("degreeRepository")
    private IDegreeRepository degreeRepository;


    private ModelMapper modelMapper = new ModelMapper();

    private DegreeModel convertToModel(Degree degree) {
        DegreeModel degreeModel = modelMapper.map(degree, DegreeModel.class);
        return degreeModel;
    }

    private Degree convertToEntity(DegreeModel degreeModel) {
        Degree degree = modelMapper.map(degreeModel, Degree.class);
        return degree;
    }

    @Override
    public List<Degree> getAll(){
        return degreeRepository.findAll();
    }

    @Override
    public DegreeModel insertOrUpdate(DegreeModel degreeModel) {
        Degree degree = degreeRepository.save(convertToEntity(degreeModel));
        return convertToModel(degree);
    }

    @Override
    public boolean remove(long id){
        try {
            degreeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
