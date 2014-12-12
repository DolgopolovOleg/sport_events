//package com.myapp.entity;
//
//import com.myapp.common.RoleList;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "user_role")
//public class UserRole {
//
//    @Id
//    @Column(name = "_id")
//    @GeneratedValue
//    private int _id;
//
////    @ManyToOne(cascade= {CascadeType.REFRESH})
////    @JoinColumn(name="user_auth_id")
////    private UserAuth userAuth;
//
//    @ManyToMany(mappedBy = "user_auth_id")
//    private Set<User> user = new HashSet<User>();
//
//    @Enumerated(EnumType.STRING)
//    private RoleList roleList;
//
//    public UserRole() {
//    }
//
////    public UserRole(UserAuth userAuth, RoleList roleList) {
////        this.userAuth = userAuth;
////        this.roleList = roleList;
////    }
//
//    public UserRole(Set<User> user, RoleList roleList) {
//        this.user = user;
//        this.roleList = roleList;
//    }
//
//    public int get_id() {
//        return _id;
//    }
//
//    public void set_id(int _id) {
//        this._id = _id;
//    }
//
//    public Set<User> getUser() {
//        return user;
//    }
//
//    public void setUser(Set<User> user) {
//        this.user = user;
//    }
//
////    public UserAuth getUserAuth() {
////        return userAuth;
////    }
////
////    public void setUserAuth(UserAuth userAuth) {
////        this.userAuth = userAuth;
////    }
//
//    public RoleList getRoleList() {
//        return roleList;
//    }
//
//    public void setRoleList(RoleList roleList) {
//        this.roleList = roleList;
//    }
//}
