package com.myapp.service;


import com.myapp.common.RoleList;
import com.myapp.entity.Activation;
import com.myapp.entity.Event;
import com.myapp.entity.User;
//import com.myapp.entity.extended.ParticipantView;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    void save(User user);
    void delete(Integer id);
    List<User> findAll();
    void createNewUser(User user);
    User getLoggedUser();
    void activateUser(User user);
    void setRole(User user, RoleList role);

    Activation findActivationForUser(User user);
//    List<ParticipantView> findAllParticipantForEvent(Event event);
}
