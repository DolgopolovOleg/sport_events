package com.myapp.dao.impl;

import com.myapp.dao.PlaceEquipmentDao;
import com.myapp.entity.Equipment;
import com.myapp.entity.Place;
import com.myapp.entity.PlaceEquipment;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlaceEquipmentDaoImpl extends AbstractDaoImpl<PlaceEquipment, Integer> implements PlaceEquipmentDao{

    protected PlaceEquipmentDaoImpl() {
        super(PlaceEquipment.class);
    }

    @Override
    public List<Map<Equipment, Integer>> findEquipmentByPlaceId(Integer placeId) {
        List<PlaceEquipment> placeEquipment = super.findByCriteria(Restrictions.eq("pl_id", (Object)placeId));
        return null;
    }

    @Override
    public Map<Equipment, Integer> findEquipmentsForPlace(Place place) {
        List<PlaceEquipment> placeEquipments = super.findByCriteria(Restrictions.eq("place", place));
        Map<Equipment, Integer> equipmentsList = new HashMap<Equipment, Integer>();
        for(PlaceEquipment placeEquipment : placeEquipments){
            Equipment equipment = placeEquipment.getEquipment();
            Integer count = placeEquipment.getCount();
            equipmentsList.put(equipment, count);
        }
        return equipmentsList;
    }
//
//    @Override
//    public boolean hasEquipment(PlaceEquipment placeEquipment){
//
//        return false;
//    }

}
