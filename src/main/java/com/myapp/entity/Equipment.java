package com.myapp.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Equipment")
public class Equipment  implements Serializable {

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
