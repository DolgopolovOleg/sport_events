package com.myapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "Equipment")
public class Equipment {

    //TODO: make validation to all fields

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private int _id;

    @Column (name = "name")
    private String name;

    @Column (name = "icon")
    private String icon;

    public Equipment() {
    }

    public Equipment(String name, String icon) {
        this.name = name;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
