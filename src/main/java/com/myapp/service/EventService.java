package com.myapp.service;

import com.myapp.entity.Event;
//import com.myapp.entity.extended.EventView;

import java.util.List;

public interface EventService {

    Event findByEventId(Integer id);
    void save(Event event);
//    EventView getEventViewByEventId(Integer id);
    List<Event> findAll();
    List<Event> findAllByUserId(Integer id);                //Return eventList with ALL user`s events
    List<Event> findAllByOrganizerId(Integer id);           //Return eventList where user is Organizer
    List<Event> findAllByParticipantId(Integer id);         //Return eventList where user is Participant


}
