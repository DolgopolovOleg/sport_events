package com.myapp.dao.impl;


import com.myapp.dao.ParticipantDao;
import com.myapp.entity.Event;
import com.myapp.entity.Participant;
import com.myapp.entity.User;
import com.myapp.entity.extended.EventView;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ParticipantDaoImpl extends AbstractDaoImpl<Participant, Integer> implements ParticipantDao{

    protected ParticipantDaoImpl(){
        super(Participant.class);
    }

    @Override
    public List<User> findAllParticipantForEvent(Event event) {

        List<User> users = new ArrayList<User>();
        List<Participant> participantsEvents = super.findByCriteria(Restrictions.eq("event", event));
        for(Participant participant : participantsEvents){
            users.add(participant.getUser());
        }
        return users;
    }

    @Override
    public List<Event> findAllEventsForUser(User user) {
        List<Event> events = new ArrayList<Event>();
        List<Participant> participantsEvents = super.findByCriteria(Restrictions.eq("user", user));
        for(Participant participant : participantsEvents){
            events.add(participant.getEvent());
        }
        return events;
    }

    @Override
    public Integer getParticipantCountForEvent(Integer eventId) {
        return super.findByCriteria(Restrictions.eq("event", (Object) eventId)).size();
    }
}
