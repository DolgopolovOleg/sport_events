package com.myapp.dao;


import com.myapp.entity.User;

public interface UserDao extends AbstractDao<User, Integer>{

    User save(User user);
    User findByEmail(String username);

}
