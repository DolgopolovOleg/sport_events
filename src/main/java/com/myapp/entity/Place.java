package com.myapp.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "place")
public class Place implements Serializable{

    @Id
    @Column(name = "_id")
    @GeneratedValue
    @JsonIgnore
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

    @OneToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="creator")
    private User creator;

    @Column(name = "created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @OneToMany(targetEntity = PlaceEquipment.class, mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<PlaceEquipment> equipments;



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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<PlaceEquipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<PlaceEquipment> equipments) {
        this.equipments = equipments;
    }

}