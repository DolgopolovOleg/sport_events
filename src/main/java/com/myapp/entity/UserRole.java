package com.myapp.entity;

import com.myapp.common.RoleList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class UserRole {

    @Id
    @Column (name = "_id")
    @GeneratedValue
    private int _id;

    @Enumerated(EnumType.STRING)
    @Column(name="title")
    private RoleList roleList;

//    @ManyToMany(mappedBy = "userRole")
//    private Set<User> user = new HashSet<User>();

    public UserRole() {
    }

    public UserRole(RoleList roleList) {
        this.roleList = roleList;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public RoleList getRoleList() {
        return roleList;
    }

    public void setRoleList(RoleList roleList) {
        this.roleList = roleList;
    }
}
