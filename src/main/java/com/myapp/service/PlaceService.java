package com.myapp.service;

import com.myapp.entity.Equipment;
import com.myapp.entity.Place;
import com.myapp.entity.PlaceEquipment;

import java.util.List;
import java.util.Map;

public interface PlaceService {

    List<Place> findByCreatorId(Integer creatorId);
    List<Place> findAll();
    Place findById(Integer id);
    void save(Place place);
//    com.myapp.entity.extended.PlaceView getPlaceViewByPlaceId(Integer placeId);

//    List<Map<Equipment, Integer>> findEquipmentByPlaceId(Integer placeId);
//    Map<Equipment, Integer> findEquipmentsForPlace(Place place);
//    void save(PlaceEquipment placeEquipment);
}
