package com.myapp.dao;


import com.myapp.entity.Event;

import java.util.List;

public interface EventDao extends AbstractDao<Event, Integer>{

    List<Event> findAllByUserId(Integer id);                //Return eventList with ALL user`s events
    List<Event> findAllByOrganizerId(Integer id);           //Return eventList where user is Organizer
    List<Event> findAllByParticipantId(Integer id);         //Return eventList where user is Participant

}
