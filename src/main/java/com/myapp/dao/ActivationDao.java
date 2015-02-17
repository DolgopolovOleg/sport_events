package com.myapp.dao;


import com.myapp.entity.Activation;
import com.myapp.entity.User;

public interface ActivationDao extends AbstractDao<Activation, Integer>{

    Activation findByUser(User user);

}
