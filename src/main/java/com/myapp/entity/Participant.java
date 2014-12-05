package com.myapp.entity;


import javax.persistence.*;

@Entity
@Table (name="participant")
public class Participant {

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private int _id;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="event_id")
    private Event event;

    @Column(name = "role")
    private String role;

    public Participant() {
    }

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
