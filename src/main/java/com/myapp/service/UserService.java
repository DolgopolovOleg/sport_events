package com.myapp.service;


import com.myapp.entity.Event;
import com.myapp.entity.User;
import com.myapp.entity.extended.ParticipantView;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    void save(User user);
    void delete(Integer id);
    List<User> findAll();

    List<ParticipantView> findAllParticipantForEvent(Event event);
}
