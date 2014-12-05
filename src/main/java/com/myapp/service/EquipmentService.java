package com.myapp.service;



import com.myapp.entity.Equipment;

import java.util.List;

public interface EquipmentService {
    Equipment findById(Integer id);
    Equipment findByName(String name);
    void save(Equipment equipment);
    List<Equipment> findAll();
}
