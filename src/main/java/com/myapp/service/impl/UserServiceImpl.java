package com.myapp.service.impl;


//import com.myapp.dao.ParticipantDao;
import com.myapp.common.ActivationCodeReason;
import com.myapp.common.RoleList;
import com.myapp.dao.ActivationDao;
import com.myapp.dao.UserDao;
import com.myapp.entity.*;
//import com.myapp.entity.extended.ParticipantView;
import com.myapp.helpers.PasswordHelper;
import com.myapp.mail_utils.MessageProducer;
import com.myapp.service.UserService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivationDao activationDao;

    @Autowired
    private MessageProducer messageProducer;

//    @Autowired
//    private ParticipantDao participantDao;

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(Integer id) {
        User user = userDao.findById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }

    @Override
    public List<User> findAll(){
        Example example = Example.create(new User()).excludeProperty("password");
        return userDao.findAll(example);
    }

    @Override
    public void createNewUser(User user) {
        //TODO: save password in MD5
        //TODO: send password to user

        //create PasswordHelper entity
        PasswordHelper passwordHelper = new PasswordHelper();
        //set MD5 password from user password
        user.setPassword( passwordHelper.encode(user.getPassword()) );
        user.setEnabled(false);
        this.save(user);
        Activation activation = new Activation(user, ActivationCodeReason.REGISTRATION);
        activationDao.saveOrUpdate(activation);
        //send message to user
        messageProducer.sendUserRegistrationMessage(activation);
//        messageProducer.sendMessages(activation.getUser());
    }

    @Override
    public User getLoggedUser(){
        //TODO: return string if not authorize
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser")
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    @Override
    public void activateUser(User user) {
        user.setEnabled(true);
        this.setRole(user, RoleList.USER);
        userDao.save(user);
    }

    @Override
    public void setRole(User user, RoleList role) {
        UserRole userRole = new UserRole(user, role);
        user.getUserRole().add(userRole);
        userDao.save(user);
    }

    @Override
    public Activation findActivationForUser(User user) {
        return activationDao.findByUser(user);
    }

//    @Override
//    public List<ParticipantView> findAllParticipantForEvent(Event event) {
//        return participantDao.findAllParticipantForEvent(event);
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByEmail(s);
        if(user == null) throw new UsernameNotFoundException("username: " + s + "not found!");
        return user;
    }
}
