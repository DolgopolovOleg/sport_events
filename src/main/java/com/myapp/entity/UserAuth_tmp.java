//package com.myapp.entity;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import java.util.*;
//
//
//@Entity
//@Table(name = "user_auth")
//public class UserAuth{
//
//    @Id
//    @Column(name = "_id")
//    @GeneratedValue
//    private int _id;
//
////    @ManyToOne(cascade= {CascadeType.REFRESH})
////    @JoinColumn(name="user_id")
////    private User user;
//
//    @Column (name = "login")
//    @Size(min=1, max=15, message="user_auth_login_size_message")
//    @Pattern(regexp="^[a-zA-Z0-9]+$", message="user_auth_login_pattern_message")
//    private String login;
//
//    @Column (name = "password")
//    @Size(min=6, max=15, message="user_auth_password_size_message")
//    @Pattern(regexp="^[a-zA-Z0-9]+$", message="user_auth_password_pattern_message")
//    private String password;
//
////    @Column (name = "enabled")
////    private Integer enabled;
//
//    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_auth_id"),
//    inverseJoinColumns = @JoinColumn(name="role_id"))
//    private Set<UserRole> userRole = new HashSet<UserRole>();
//
//    public UserAuth() {
//    }
//
//    public UserAuth(User user, String login, String password) {
////        this.user = user;
//        this.login = login;
//        this.password = password;
////        this.enabled = enabled;
//    }
//
//    //    @Column (name = "role")
////    private String role;
//
////    private Set<>
//
//    public int get_id() {
//        return _id;
//    }
//
//    public void set_id(int _id) {
//        this._id = _id;
//    }
//
////    public User getUser() {
////        return user;
////    }
////
////    public void setUser(User user) {
////        this.user = user;
////    }
//
////    public String getLogin() {
////        return login;
////    }
//
////    public void setLogin(String login) {
////        this.login = login;
////    }
//
////    public void setPassword(String password) {
////        this.password = password;
////    }
//
////    public Integer getEnabled() {
////        return enabled;
////    }
//
////    public void setEnabled(Integer enabled) {
////        this.enabled = enabled;
////    }
//
////    public String getRole() {
////        return role;
////    }
////
////    public void setRole(String role) {
////        this.role = role;
////    }
//
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        List<SimpleGrantedAuthority> result = new ArrayList<SimpleGrantedAuthority>();
////
////        for(UserRole userRole: this.userRole){
////            result.add(new SimpleGrantedAuthority(userRole.getRoleList().name()));
////        }
////
////        return null;
////    }
////
////    @Override
////    public String getPassword() {
////        return this.getPassword();
////    }
////
////    @Override
////    public String getUsername() {
////        return this.getUsername();
////    }
////
////    @Override
////    public boolean isAccountNonExpired() {
////        return true;
////    }
////
////    @Override
////    public boolean isAccountNonLocked() {
////        Boolean isAccountNonLocked = this.getEnabled() == 1;
////        return true;
////    }
////
////    @Override
////    public boolean isCredentialsNonExpired() {
////        return true;
////    }
////
////    @Override
////    public boolean isEnabled() {
////        return true;
////    }
//
//}
