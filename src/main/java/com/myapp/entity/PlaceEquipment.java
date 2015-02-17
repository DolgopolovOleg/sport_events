package com.myapp.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "place_equipment")
public class PlaceEquipment implements Serializable {

    @Id
    @Column (name = "_id")
    @GeneratedValue
    private int _id;

    @ManyToOne(cascade={CascadeType.REFRESH})
    @JoinColumn(name="pl_id")
    private Place place;

    @OneToOne(cascade={CascadeType.REFRESH})
    @JoinColumn(name="eq_id")
    private Equipment equipment;

    @Column(name = "count")
    private int count;

    public PlaceEquipment() {
    }

        public PlaceEquipment(Place place, Equipment equipment, int count) {
        this.place = place;
        this.equipment = equipment;
        this.count = count;
    }

    @JsonIgnore
    public int get_id() {
        return _id;
    }

    public int getId() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @JsonIgnore
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
