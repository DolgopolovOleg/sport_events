package com.myapp.service.impl;


import com.myapp.dao.ParticipantDao;
import com.myapp.dao.UserDao;
import com.myapp.entity.Event;
import com.myapp.entity.Participant;
import com.myapp.entity.User;
import com.myapp.entity.extended.ParticipantView;
import com.myapp.service.UserService;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private ParticipantDao participantDao;

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
        return userDao.findAll();
    }

    @Override
    public List<ParticipantView> findAllParticipantForEvent(Event event) {
        return participantDao.findAllParticipantForEvent(event);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByUsername(s);
        if(user == null) throw new UsernameNotFoundException("username: " + s + "not found!");
        return user;
    }
}
