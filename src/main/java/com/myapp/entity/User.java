package com.myapp.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table (name = "user")
public class User implements UserDetails{

    @Id
    @Column (name = "_id")
    @GeneratedValue
    private int _id;

    @Column (name = "name")
    @Size(min=1, max=30, message="user_save_name_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_name_pattern_message")
    private String name;

    @Column (name = "sname")
    @Size(min=1, max=30, message="user_save_sname_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_sname_pattern_message")
    private String sname;

    @Column (name = "nickname")
    @Size(min=1, max=30, message="user_save_nickname_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="user_save_nickname_pattern_message")
    private String nickname;

    @Column (name = "phone")
    @Size(min=3, max=30, message="user_save_phone_size_message")
    @Pattern(regexp="^[+0-9 )(]+$", message="user_save_phone_pattern_message")
    private String phone;

    @Column (name = "email")
    @Pattern(regexp="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}", message="user_save_email_pattern_message")
    private String email;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    public User() {
    }

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<UserRole> userRole = new HashSet<UserRole>();

    public User(String name, String sname, String nickname, String phone, String email, String username, String password) {
        this.name = name;
        this.sname = sname;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> result = new ArrayList<SimpleGrantedAuthority>();

        for(UserRole userRole: this.userRole){
            result.add(new SimpleGrantedAuthority(userRole.getRoleList().name()));
        }

        return null;
    }

}
