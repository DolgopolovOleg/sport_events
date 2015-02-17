package com.myapp.mail_utils;

import com.myapp.entity.Activation;

import java.util.HashMap;
import java.util.Map;


public class MessagePropertiesCompiler {

    public static Map<String, Object> compileUserRegistationProperties(Activation activation){
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("header", "Header");
        properties.put("user", activation.getUser());
        properties.put("userID", activation.getUser().get_id());
        properties.put("activationCode", activation.getActivationCode());
        return properties;
    }

}
