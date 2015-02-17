package com.myapp.alerts;


import com.myapp.entity.Activation;
import com.myapp.entity.Event;
import com.myapp.entity.User;
import com.myapp.service.MailService;

import javax.annotation.Resource;

public class UserAlertHandler {

    @Resource(name = "mailService")
    private MailService mailService;

    public void processUser(){
        System.out.println("Do some");
    }

    public void processUser(User user){
        System.out.println("Do some");
    }

    public void processEvent(){
        System.out.println("Do some");
    }

    public void processEvent(Event event){
        System.out.println("Do some");
    }

    public void processActivation(){
        System.out.println("Do some");
    }

    public void processActivation(Activation activation){
        //Compile Letter and send it
        System.out.println("Process activation");
        mailService.sendUserRegistrationMail(activation);
    }

}
