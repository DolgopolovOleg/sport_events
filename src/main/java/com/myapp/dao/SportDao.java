package com.myapp.dao;


import com.myapp.entity.Sport;

public interface SportDao extends AbstractDao<Sport, Integer> {

    Sport findByName(String name);

}
