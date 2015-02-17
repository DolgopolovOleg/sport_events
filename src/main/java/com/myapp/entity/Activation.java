package com.myapp.entity;


import com.myapp.common.ActivationCodeReason;
import com.myapp.helpers.PasswordHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "activation")
public class Activation implements Serializable{

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private int _id;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "activation_code")
    private String activationCode;

    @Enumerated(EnumType.STRING)
    @Column(name="reason")
    private ActivationCodeReason reason;

    public Activation() {
    }

    public Activation(User user, ActivationCodeReason reason) {
        this.user = user;
        PasswordHelper passwordHelper = new PasswordHelper();
        Date date = new Date();
        String generatedActivationCode = date.toString() + user.getEmail() + user.getPassword();
        generatedActivationCode = passwordHelper.encode(generatedActivationCode);
//        this.activationCode = activationCode;
        this.activationCode = generatedActivationCode;
        this.reason = reason;
    }

    public int get_id() {
        return _id;
    }

    public int getId() {
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public ActivationCodeReason getReason() {
        return reason;
    }

    public void setReason(ActivationCodeReason reason) {
        this.reason = reason;
    }
}
