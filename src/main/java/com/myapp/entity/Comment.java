package com.myapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment")
public class Comment {

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private int _id;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @Column (name = "text", columnDefinition="TEXT")
    private String text;

    @Column(name = "created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "from")
    private String from;


    @Column(name = "from_id")
    private Integer from_id;

    public Comment() {
    }

    public Comment(User user, String text, Date created, String from, Integer from_id) {
        this.user = user;
        this.text = text;
        this.created = created;
        this.from = from;
        this.from_id = from_id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }
}
