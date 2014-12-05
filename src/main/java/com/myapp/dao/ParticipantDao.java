package com.myapp.dao;


import com.myapp.entity.Event;
import com.myapp.entity.Participant;
import com.myapp.entity.User;
import com.myapp.entity.extended.EventView;

import java.util.List;

public interface ParticipantDao extends AbstractDao<Participant, Integer>{

    List<User> findAllParticipantForEvent(Event event);
    List<Event> findAllEventsForUser(User user);
    Integer getParticipantCountForEvent(Integer eventId);

}
