//package com.myapp.service.impl;
//
//import com.myapp.dao.PlaceEquipmentDao;
//import com.myapp.dao.impl.PlaceEquipmentDaoImpl;
//import com.myapp.entity.Equipment;
//import com.myapp.entity.Place;
//import com.myapp.entity.PlaceEquipment;
//import com.myapp.service.PlaceEquipmentService;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
//@Service("placeEquipmentService")
//@Transactional(readOnly = false)
//public class PlaceEquipmentServiceImpl implements PlaceEquipmentService{
//
//    @Autowired
//    PlaceEquipmentDao placeEquipmentDao;
//
//    @Override
//    public List<Map<Equipment, Integer>> findEquipmentByPlaceId(Integer placeId) {
//        return placeEquipmentDao.findEquipmentByPlaceId(placeId);
//    }
//
//    @Override
//    public Map<Equipment, Integer> findEquipmentsForPlace(Place place) {
//        return placeEquipmentDao.findEquipmentsForPlace(place);
//    }
//
//    @Override
//    public void save(PlaceEquipment placeEquipment) {
//        if(!this.hasSameEquipment(placeEquipment))
//            placeEquipmentDao.saveOrUpdate(placeEquipment);
//    }
//
//    private boolean hasSameEquipment(PlaceEquipment placeEquipment){
//        List<PlaceEquipment> PlEqList = ((PlaceEquipmentDaoImpl) placeEquipmentDao).getCurrentSession().createCriteria(PlaceEquipment.class)
//                .add(Restrictions.eq("place", placeEquipment.getPlace()))
//                .add(Restrictions.eq("equipment", placeEquipment.getEquipment()))
//                .list();
//        if(PlEqList.size() > 0)
//            return true;
//        return false;
//    }
//}
