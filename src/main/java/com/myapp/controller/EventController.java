package com.myapp.controller;


import com.myapp.entity.Comment;
import com.myapp.entity.Event;
import com.myapp.entity.User;
import com.myapp.entity.extended.EventView;
import com.myapp.entity.extended.ParticipantView;
import com.myapp.entity.extended.PlaceView;
import com.myapp.service.CommentService;
import com.myapp.service.EventService;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value="/events")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;


    @RequestMapping(value="")
    public String showAllEvents(Model model){
        List<Event> events= eventService.findAll();
        model.addAttribute("events", events);
        return "events";
    }

    @RequestMapping(value = "/{eventID}", method = RequestMethod.GET)
    public String showUser(
            @PathVariable Integer eventID,
            Model model){
//        TODO: id check (if not an Integer or do not exist Place)
        model.addAttribute("title", "Events");
        Event event = eventService.findByEventId(eventID);
        EventView eventView = eventService.getEventViewByEventId(eventID);
        List<ParticipantView> users = userService.findAllParticipantForEvent(event);
        model.addAttribute("eventView", eventView);
        List<Comment> comments = commentService.findByFromAndFromId("EVENT", 1);
        return "event_info";
    }


}
