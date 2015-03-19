package com.myapp.mail_utils;

import com.myapp.entity.Activation;
import com.myapp.entity.Event;
import com.myapp.entity.User;

public interface MessageProducer {

    void sendMessages(User user);
    void sendMessages(Event event);
    void sendUserRegistrationMessage(Activation activation);

}
