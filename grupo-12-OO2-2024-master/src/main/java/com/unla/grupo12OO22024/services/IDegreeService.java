package com.unla.grupo12OO22024.services;

import java.util.List;

import com.unla.grupo12OO22024.models.DegreeModel;
import com.unla.grupo12OO22024.entities.Degree;

public interface IDegreeService {
    public List<Degree> getAll();
    public DegreeModel insertOrUpdate(DegreeModel degreeModel);
    public boolean remove(long id);
    
}