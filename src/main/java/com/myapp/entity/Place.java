package com.myapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "place")
public class Place {

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private int _id;

    @Column (name = "name")
    @Size(min=1, max=30, message="user_save_name_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_name_pattern_message")
    private String name;

    @Column (name = "longitude")
    private String longitude;

    @Column (name = "latitude")
    private String latitude;

    @Column (name = "description", columnDefinition="TEXT")
    private String description;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="creator")
    private User creator;

    public Place() {
    }

    public Place(String name, String longitude, String latitude, String description, User creator) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
        this.creator = creator;
//        this.equipmentList = equipmentList;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

}