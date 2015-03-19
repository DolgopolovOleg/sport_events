//package com.myapp.entity.authentications;
//
//
//import com.myapp.entity.User;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "user_auth_facebook")
//public class FacebookAuthUser extends User{
//
//    @ Column(name = "fb_id", length = 32)
//    private String fbId;
//
//    @ Column(name = "first_name", length = 32)
//    private String firstName;
//
//    @ Column(name = "last_name", length = 32)
//    private String lastName;
//
//    @ Column(name = "email", length = 64, insertable = false, updatable = false)
//    private String email;
//
//    @Column(name = "token", length = 128)
//    private String token;
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//}
