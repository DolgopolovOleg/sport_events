package com.myapp.entity.extended;

import com.myapp.entity.Event;
import com.myapp.entity.Place;
import com.myapp.entity.Sport;

import java.util.Date;

public class EventView{

    protected Event event;
    protected PlaceView placeView;

    public EventView() {
    }

    public EventView(Event event, PlaceView placeView) {
        this.event = event;
        this.placeView = placeView;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public PlaceView getPlaceView() {
        return placeView;
    }

    public void setPlaceView(PlaceView placeView) {
        this.placeView = placeView;
    }
}
