package com.myapp.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table (name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User implements UserDetails, Serializable {

    @Id
    @Column (name = "_id")
    @GeneratedValue
    private int _id;

    @Column (name = "name", nullable = true)
    @Size(min=3, max=30, message="user_save_name_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_name_pattern_message")
    private String name;

    @Column (name = "sname", nullable = true)
    @Size(min=3, max=30, message="user_save_sname_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_sname_pattern_message")
    private String sname;

//    @Column (name = "nickname", nullable = true)
//    @Size(min=1, max=30, message="user_save_nickname_size_message")
//    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_nickname_pattern_message")
//    private String nickname;

    @Column (name = "phone")
//    @Size(min=0, max=30, message="user_save_phone_size_message")
//    @Pattern(regexp="^[+0-9 )(]+$", message="user_save_phone_pattern_message")
    private String phone;

    @Column (name = "email", unique = true)
//    @Column (name = "email")
    @Pattern(regexp="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}", message="user_save_email_pattern_message")
    private String email;

//    @Column (name = "username")
//    private String username;

    @Column (name = "password")
    @Size(min=3, max=40, message="user_save_password_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_password_pattern_message")
    private String password;

    @Column (name = "enabled")
    @Type(type = "org.hibernate.type.NumericBooleanType")
//    private Integer enabled;
    private Boolean enabled;

    @OneToMany(targetEntity = UserRole.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<UserRole> userRole = new ArrayList<UserRole>();

    @JsonIgnore
    public List<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<UserRole> userRole) {
        this.userRole = userRole;
    }

    public User() {
    }

    public User(String name, String sname, String phone, String email, String password, Boolean enabled) {
        this.name = name;
        this.sname = sname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.getEmail();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.getEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> result = new ArrayList<SimpleGrantedAuthority>();

        for(UserRole userRole: this.getUserRole()){
            result.add(new SimpleGrantedAuthority(userRole.getRoleList().name()));
        }

        return result;
    }

//    public static User getLoggedUser(){
//        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }

}
