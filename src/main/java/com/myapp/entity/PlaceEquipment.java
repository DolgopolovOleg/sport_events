package com.myapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "place_equipment")
public class PlaceEquipment {

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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

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
