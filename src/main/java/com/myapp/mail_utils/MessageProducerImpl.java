package com.myapp.mail_utils;


import com.myapp.entity.Activation;
import com.myapp.entity.Event;
import com.myapp.entity.User;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Map;


@Service("mailProducer")
public class MessageProducerImpl implements MessageProducer{

    protected JmsTemplate jmsTemplate;

    public MessageProducerImpl() {
    }

    public MessageProducerImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessages(final User user)
    {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage(user);
                return message;
            }
        };
        jmsTemplate.send(messageCreator);
    }

    public void sendMessages(final Event event)
    {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage(event);
                return message;
            }
        };
        jmsTemplate.send(messageCreator);
    }

    @Override
    public void sendUserRegistrationMessage(final Activation activation) {
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage(activation);
                return message;
            }
        };
        jmsTemplate.send(messageCreator);
    }

}
