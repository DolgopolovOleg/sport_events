package com.myapp.service;


import com.myapp.common.RoleList;
import com.myapp.entity.Activation;
import com.myapp.entity.User;
import com.myapp.registration.DuplicateEmailException;
import com.myapp.registration.RegistrationForm;
import org.springframework.social.connect.UserProfile;
//import com.myapp.entity.extended.ParticipantView;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    void save(User user);
    void delete(Integer id);
    List<User> findAll();
    User createNewUser(User user) throws DuplicateEmailException;
    User registrateNewUser(User user) throws DuplicateEmailException;
    User getLoggedUser();
    void activateUser(User user);
    void setRole(User user, RoleList role);
    User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
    User createUserFromUserProfile(UserProfile userProfile);

    Activation findActivationForUser(User user);
//    List<ParticipantView> findAllParticipantForEvent(Event event);
}
