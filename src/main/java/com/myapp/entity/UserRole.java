package com.myapp.entity;

import com.myapp.common.RoleList;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@Transactional(readOnly = false)
public class UserRole {

    @Id
    @Column (name = "_id")
    @GeneratedValue
    private int _id;

    @OneToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(name="role_name")
    private RoleList roleList;

    public UserRole() {
    }

    public UserRole(User user, RoleList roleList) {
        this.user = user;
        this.roleList = roleList;
    }

    @JsonIgnore
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoleList getRoleList() {
        return roleList;
    }

    public void setRoleList(RoleList roleList) {
        this.roleList = roleList;
    }
}
