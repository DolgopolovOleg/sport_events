package com.myapp.service;

import com.myapp.entity.Sport;

import java.util.List;

public interface SportService {

    Sport findById(Integer id);
    void save(Sport sport);
    Sport findByName(String name);
    List<Sport> findAll();

}
