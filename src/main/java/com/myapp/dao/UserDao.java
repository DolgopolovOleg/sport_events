package com.myapp.dao;


import com.myapp.entity.User;

public interface UserDao extends AbstractDao<User, Integer>{

    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);

}
