package com.myapp.dao;


import com.myapp.entity.Equipment;

public interface EquipmentDao extends AbstractDao<Equipment, Integer>{
    void save(Equipment equipment);
    Equipment findByName(String name);
}
