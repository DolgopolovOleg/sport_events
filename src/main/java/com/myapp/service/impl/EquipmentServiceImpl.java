package com.myapp.service.impl;


import com.myapp.dao.EquipmentDao;
import com.myapp.entity.Equipment;
import com.myapp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("equipmentService")
@Transactional(readOnly = true)
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public Equipment findById(Integer id) {
        return equipmentDao.findById(id);
    }

    @Override
    public Equipment findByName(String name) {
        return equipmentDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Equipment equipment) {
        equipmentDao.save(equipment);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentDao.findAll();
    }

}
