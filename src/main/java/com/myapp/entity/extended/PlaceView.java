package com.myapp.entity.extended;

import com.myapp.entity.Equipment;
import com.myapp.entity.Place;
import com.myapp.service.PlaceService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class PlaceView{

    protected Place place;
    protected Map<Equipment, Integer> equipments;

    public PlaceView() {
    }

    public PlaceView(Place place, Map<Equipment, Integer> equipments) {
        this.place = place;
        this.equipments = equipments;
    }

    public Map<Equipment, Integer> getEquipments() {
        return equipments;
    }

    public void setEquipments(Map<Equipment, Integer> equipments) {
        this.equipments = equipments;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

}
