package com.myapp.dao.impl;


import com.myapp.dao.EquipmentDao;
import com.myapp.entity.Equipment;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentDaoImpl extends AbstractDaoImpl<Equipment, Integer> implements EquipmentDao{

    protected EquipmentDaoImpl() {
        super(Equipment.class);
    }

    @Override
    public void save(Equipment equipment) {
        super.saveOrUpdate(equipment);
    }

    @Override
    public Equipment findByName(String name) {
        return (Equipment) super.findByCriterion(Restrictions.eq("name", (Object) name)).get(0);
    }

}
