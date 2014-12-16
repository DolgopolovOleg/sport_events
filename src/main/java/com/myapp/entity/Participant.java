package com.myapp.entity;


import com.myapp.common.ParticipantRole;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ParticipantRole role;

    public Participant() {
    }

    public Participant(User user, Event event, ParticipantRole role) {
        this.user = user;
        this.event = event;
        this.role = role;
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

    public ParticipantRole getRole() {
        return role;
    }

    public void setRole(ParticipantRole role) {
        this.role = role;
    }
}
