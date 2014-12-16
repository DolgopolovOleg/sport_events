package com.myapp.entity;

//import com.myapp.entity.extended.PlaceView;
import com.myapp.service.PlaceService;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity

@Table(name="event")
public class Event {

//    @Resource(name="placeService")
//    private PlaceService placeService;

    @Id
    @Column(name = "_id")
    @GeneratedValue
    private int _id;

    @Column (name = "name")
    @Size(min=1, max=30, message="event_save_name_size_message")
    @Pattern(regexp="^[а-яА-Яa-zA-Z0-9_]+$", message="event_save_name_pattern_message")
    private String name;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="sport")
    private Sport sport;

    @Column (name = "description", columnDefinition="TEXT")
    private String description;

    @ManyToOne(cascade= {CascadeType.REFRESH})
    @JoinColumn(name="place_id")
    private Place place;

    @Column(name = "date_start", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @Column(name = "date_finish", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinish;

    @OneToMany(targetEntity = Participant.class, mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Participant> participants;

    public Event() {
    }

    public Event(String name, Sport sport, String description, Place place, Date dateStart, Date dateFinish) {
        this.name = name;
        this.sport = sport;
        this.description = description;
        this.place = place;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    //    public PlaceView getPlaceView(Place place){
//        return
//    }
}
