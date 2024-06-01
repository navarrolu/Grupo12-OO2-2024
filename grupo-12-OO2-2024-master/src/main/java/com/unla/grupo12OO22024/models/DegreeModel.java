package com.unla.grupo12OO22024.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;

@Getter
@Setter
@NoArgsConstructor
public class DegreeModel {
    @Id
    @Setter(AccessLevel.PROTECTED)
    private long id;
    private String name;
    private String institution;
    private int year;

    public DegreeModel(long id, String name, String institution, int year) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.year = year;
    }
}