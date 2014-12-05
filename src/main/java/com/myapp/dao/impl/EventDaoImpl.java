package com.myapp.dao.impl;

import com.myapp.dao.EventDao;
import com.myapp.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<Event, Integer> implements EventDao{

    protected EventDaoImpl() {
        super(Event.class);
    }

    @Override
    public List<Event> findAllByUserId(Integer id) {
        //TODO: create method
        return null;
    }

    @Override
    public List<Event> findAllByOrganizerId(Integer id) {
        //TODO: create method
        return null;
    }

    @Override
    public List<Event> findAllByParticipantId(Integer id) {
        //TODO: create method
        return null;
    }
}
