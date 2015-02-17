package com.myapp.service;


import com.myapp.entity.Activation;

public interface AuthService {

    void save(Activation activation);
    Activation findByUserID(Integer userID);

}
