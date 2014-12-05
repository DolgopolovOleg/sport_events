package com.myapp.service.impl;

import com.myapp.dao.EventDao;
import com.myapp.entity.Event;
import com.myapp.entity.extended.EventView;
import com.myapp.entity.extended.PlaceView;
import com.myapp.service.EventService;
import com.myapp.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("eventService")
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    @Autowired
    EventDao eventDao;

    @Autowired
    PlaceService placeService;

    @Override
    public Event findByEventId(Integer id) {
        return eventDao.findById(id);
    }

    @Override
    public EventView getEventViewByEventId(Integer id) {
        Event event = this.findByEventId(id);
        PlaceView placeView = placeService.getPlaceViewByPlaceId(event.getPlace().get_id());
        EventView eventView = new EventView(event, placeView);

        return eventView;
    }

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public List<Event> findAllByUserId(Integer id) {
        return null;
    }

    @Override
    public List<Event> findAllByOrganizerId(Integer id) {
        return null;
    }

    @Override
    public List<Event> findAllByParticipantId(Integer id) {
        return null;
    }
}
