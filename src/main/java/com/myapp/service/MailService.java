package com.myapp.service;

import com.myapp.entity.Activation;
import com.myapp.entity.User;

public interface MailService {

    void sendUserMail(String to, User user);
    void sendUserRegistrationMail(Activation activation);

}
