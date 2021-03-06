package com.myapp.service.impl;

import com.myapp.dao.PlaceDao;
import com.myapp.entity.Comment;
import com.myapp.entity.Equipment;
import com.myapp.entity.Place;
import com.myapp.entity.PlaceEquipment;
import com.myapp.service.CommentService;
import com.myapp.service.PlaceService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("placeService")
@Transactional(readOnly = false)
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceDao placeDao;

    @Autowired
    CommentService commentService;

    @Override
    public List<Place> findByCreatorId(Integer creatorId) {
        return null;
    }

    @Override
    public List<Place> findAll() {
        return placeDao.findAll();
    }

    @Override
    public Place findById(Integer id){
        return placeDao.findById(id);
    }

    @Override
    public void save(Place place) {
        placeDao.saveOrUpdate(place);
    }

    @Override
    public void delete(Place place) {
        placeDao.delete(place);
    }

//    @Override
//    public PlaceView getPlaceViewByPlaceId(Integer placeId) {
//        Place place = placeDao.findById(placeId);
//        Map<Equipment, Integer> equipments = placeEquipmentDao.findEquipmentsForPlace(place);
//
//        List<Comment> comments = commentService.findByFromAndFromId("PLACE", placeId);
//        return new PlaceView(place, equipments, comments);
//    }

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

    private boolean hasSameEquipment(PlaceEquipment placeEquipment){
//        List<PlaceEquipment> PlEqList = ((PlaceEquipmentDaoImpl) placeEquipmentDao).getCurrentSession().createCriteria(PlaceEquipment.class)
//                .add(Restrictions.eq("place", placeEquipment.getPlace()))
//                .add(Restrictions.eq("equipment", placeEquipment.getEquipment()))
//                .list();
//        if(PlEqList.size() > 0)
//            return true;
        return false;
    }


}
